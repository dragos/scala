package scala.reflect.api

trait PresentationSymbols { self: Names with PresentationTypes =>

  type Symbol >: Null <: PresentationSymbol

  trait PresentationSymbol {

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

    /** An id number which is unique for all symbols in this universe */
    def id: Int

    /** If symbol is an object definition, its implied associated class,
     *  otherwise NoSymbol
     */
    def moduleClass: Symbol // needed for LiftCode

    /** If this symbol is a top-level class, this symbol; otherwise the next enclosing
     *  top-level class, or `NoSymbol` if none exists.
     */
    def enclosingTopLevelClass: Symbol

    /** If this symbol is a class, this symbol; otherwise the next enclosing
     *  class, or `NoSymbol` if none exists.
     */
    def enclosingClass: Symbol

    /** If this symbol is a method, this symbol; otherwise the next enclosing
     *  method, or `NoSymbol` if none exists.
     */
    def enclosingMethod: Symbol

    /** If this symbol is a package class, this symbol; otherwise the next enclosing
     *  package class, or `NoSymbol` if none exists.
     */
    def enclosingPackageClass: Symbol

    /** Does this symbol represent the definition of term?
     *  Note that every symbol is either a term or a type.
     *  So for every symbol `sym`, either `sym.isTerm` is true
     *  or `sym.isType` is true.
     */
    def isTerm: Boolean

    /** Does this symbol represent the definition of type?
     *  Note that every symbol is either a term or a type.
     *  So for every symbol `sym`, either `sym.isTerm` is true
     *  or `sym.isType` is true.
     */
    def isType: Boolean

    /** Does this symbol represent the definition of class?
     *  If yes, `isType` is also guaranteed to be true.
     */
    def isClass: Boolean

    /** Does this symbol represent the definition of a type alias?
     *  If yes, `isType` is also guaranteed to be true.
     */
    def isAliasType: Boolean

    /** Does this symbol represent the definition of an abstract type?
     *  If yes, `isType` is also guaranteed to be true.
     */
    def isAbstractType: Boolean

  }

  val NoSymbol: Symbol
}