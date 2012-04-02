/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Martin Odersky
 */

package scala.reflect
package api

trait StandardDefinitions extends PresentationStandardDefinitions { self: Universe =>

  val definitions: AbsDefinitions

  abstract class AbsDefinitions extends PresentationAbsDefinitions {
    /** Given a type T, returns the type corresponding to the VM's
     *  representation: ClassClass's type constructor applied to `arg`.
     */
    def vmClassType(arg: Type): Type    // !!! better name?

    /** The string representation used by the given type in the VM.
     */
    def vmSignature(sym: Symbol, info: Type): String

    /** Is symbol one of the value classes? */
    def isPrimitiveValueClass(sym: Symbol): Boolean        // !!! better name?

    /** Is symbol one of the numeric value classes? */
    def isNumericValueClass(sym: Symbol): Boolean   // !!! better name?
  }
}
