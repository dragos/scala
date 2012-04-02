package scala.reflect
package api

trait Scopes { self: PresentationSymbols =>

  type Scope <: Iterable[Symbol]

  def newScope(): Scope
}


