package scala.tools.nsc.interactive

import scala.reflect.generic

/** Type and value aliases for source compatibility between
 *  2.9 and 2.10. Used by the IDE
 */
object Compatibility {
  type PresentationSymbols             = generic.interactive.Symbols
  type PresentationTypes               = generic.interactive.Types
  type PresentationStandardDefinitions = generic.interactive.StandardDefinitions
  
  type Positions     = scala.tools.nsc.interactive.Positions
  type Names         = generic.interactive.Names
  type StandardNames = generic.StdNames
  type Constants     = generic.interactive.Constants
  type Scopes        = generic.interactive.Scopes

  type Trees = generic.Trees
  type TreePrinters = generic.interactive.TreePrinters
}
