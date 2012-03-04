package scala.tools.nsc
package util

import scala.collection.mutable

class WorkScheduler {

  type Action = () => Unit

  private var todo = new mutable.Queue[Action]
  private var throwables = new mutable.Queue[Throwable]
  private var interruptReqs = new mutable.Queue[InterruptReq]
  @volatile private var shuttingDown = false

  /** Called from server: block until one of todo list, throwables or interruptReqs is nonempty */
  def waitForMoreWork() = synchronized {
    while (todo.isEmpty && throwables.isEmpty && interruptReqs.isEmpty) { wait() }
  }

  /** called from Server: test whether one of todo list, throwables, or InterruptReqs is nonempty */
  def moreWork: Boolean = synchronized {
    todo.nonEmpty || throwables.nonEmpty || interruptReqs.nonEmpty
  }

  /** Called from server: get first action in todo list, and pop it off */
  def nextWorkItem(): Option[Action] = synchronized {
    if (todo.isEmpty) None else Some(todo.dequeue())
  }

  def dequeueAll[T](f: Action => Option[T]): Seq[T] = synchronized {
    todo.dequeueAll(a => f(a).isDefined).map(a => f(a).get)
  }

  /** Called from server: return optional exception posted by client
   *  Reset to no exception.
   */
  def pollThrowable(): Option[Throwable] = synchronized {
    if (throwables.isEmpty)
      None
    else {
      val result = Some(throwables.dequeue())
      if (!throwables.isEmpty)
        postWorkItem { () => }
      result
    }
  }

  def pollInterrupt(): Option[InterruptReq] = synchronized {
    if (interruptReqs.isEmpty) None else Some(interruptReqs.dequeue())
  }

  /** Called from client: have interrupt executed by server and return result */
  def doQuickly[A](op: () => A): A = if (!shuttingDown) { // shuttingDown needs to be volatile since it can't move inside the synchronized block
    val ir = new InterruptReq {
      type R = A
      val todo = op
    }
    synchronized {
      interruptReqs enqueue ir
      notify()
    }
    ir.getResult()
  } else {
    throw new FailedInterrupt(new Exception("Posted a work item to a compiler that's shutting down"))
  }

  /** Called from client: have action executed by server */
  def postWorkItem(action: Action) = synchronized {
    if (!shuttingDown) {
      todo enqueue action
      notify()
    } else action match {
        case w: CancelableAction => w.raiseMissing()
        case e: EmptyAction => // do nothing
        case _ => println("don't know what to do with this " + action.getClass)
    }
  }
  
  /** Called from client: compiler is shutting down. Ignore all requests. */
  def setShutdown() = synchronized {
    shuttingDown = true
  }
  
  /** Called from client: cancel all queued actions */
  def cancelQueued() = synchronized {
    todo.clear()
  }

  /** Called from client:
   *  Require an exception to be thrown on next poll.
   */
  def raise(exc: Throwable) = synchronized {
    throwables enqueue exc
    postWorkItem { new EmptyAction }
  }
}

class EmptyAction extends (() => Unit) {
  def apply() {}
}

trait CancelableAction extends (() => Unit) {
  /** Raise a MissingReponse, if the work item carries a response. */
  def raiseMissing(): Unit
}


