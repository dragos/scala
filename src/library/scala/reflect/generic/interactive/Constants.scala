/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Martin Odersky
 */

package scala.reflect.generic.interactive

import java.lang.Integer.toOctalString
import annotation.switch

trait Constants {
  self: Types with Symbols =>

  abstract class AbsConstant {
    val value: Any
    def tpe: Type
    def isNaN: Boolean

    def booleanValue: Boolean
    def byteValue: Byte
    def shortValue: Short
    def charValue: Char
    def intValue: Int
    def longValue: Long
    def floatValue: Float
    def doubleValue: Double
    def stringValue: String
    def typeValue: Type
    def symbolValue: Symbol

    def convertTo(pt: Type): Constant
  }

  type Constant <: AbsConstant

  val Constant: ConstantExtractor

  abstract class ConstantExtractor {
    def apply(const: Any): Constant
    def unapply(arg: Constant): Option[Any]
  }
}
