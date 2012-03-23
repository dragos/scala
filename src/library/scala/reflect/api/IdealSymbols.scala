package scala.reflect.api

trait IdealSymbols { self: Names with Types =>

  type Symbol >: Null <: IdealSymbol

  trait IdealSymbol {

    /** The owner of this symbol. This is the symbol
     *  that directly contains the current symbol's definition.
     *  The `NoSymbol` symbol does not have an owner, and calling this method
     *  on one causes an internal error.
     *  The owner of the Scala root class [[scala.reflect.api.mirror.RootClass]]
     *  and the Scala root object [[scala.reflect.api.mirror.RootPackage]] is `NoSymbol`.
     *  Every other symbol has a chain of owners that ends in
     *  [[scala.reflect.api.mirror.RootClass]].
     */
    def owner: Symbol

    /** The name of the symbol as a member of the `Name` type.
     */
    def name: Name

    /** The encoded full path name of this symbol, where outer names and inner names
     *  are separated by periods.
     */
    def fullName: String

    /** If symbol is an object definition, its implied associated class,
     *  otherwise NoSymbol
     */
    def moduleClass: Symbol // needed for LiftCode

  }

  val NoSymbol: Symbol
}