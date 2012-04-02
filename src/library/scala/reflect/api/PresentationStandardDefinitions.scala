package scala.reflect.api

trait PresentationStandardDefinitions { self: PresentationSymbols =>
  val definitions: PresentationAbsDefinitions

  abstract class PresentationAbsDefinitions {
    // packages
    def RootPackage: Symbol
    def RootClass: Symbol
    def EmptyPackage: Symbol
    def ScalaPackage: Symbol

    // top types
    def AnyClass: Symbol
    def AnyValClass: Symbol
    def AnyRefClass: Symbol
    def ObjectClass: Symbol

    // bottom types
    def NullClass: Symbol
    def NothingClass: Symbol

    // the scala value classes
    def UnitClass: Symbol
    def ByteClass: Symbol
    def ShortClass: Symbol
    def CharClass: Symbol
    def IntClass: Symbol
    def LongClass: Symbol
    def FloatClass: Symbol
    def DoubleClass: Symbol
    def BooleanClass: Symbol

    // fundamental reference classes
    def SymbolClass: Symbol
    def StringClass: Symbol
    def ClassClass: Symbol

    // product, tuple, function
    def TupleClass: Array[Symbol]
    def ProductClass: Array[Symbol]
    def FunctionClass: Array[Symbol]

    // fundamental modules
    def PredefModule: Symbol
  }
}