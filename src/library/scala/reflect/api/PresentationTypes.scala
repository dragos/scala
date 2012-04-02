package scala.reflect.api

trait PresentationTypes {

  type Type <: AnyRef

  /** This constant is used as a special value that indicates that no meaningful type exists.
   */
  val NoType: Type

  /** This constant is used as a special value denoting the empty prefix in a path dependent type.
   *  For instance `x.type` is represented as `SingleType(NoPrefix, <x>)`, where `<x>` stands for
   *  the symbol for `x`.
   */
  val NoPrefix: Type

}