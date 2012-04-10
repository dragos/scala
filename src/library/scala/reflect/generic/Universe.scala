package scala.reflect
package generic

@deprecated("scala.reflect.generic will be removed", "2.9.1")
abstract class Universe extends Symbols
                           with Types
                           with Constants
                           with interactive.Positions
                           with interactive.StandardDefinitions
                           with Scopes
                           with Names
                           with StdNames
                           with Trees
                           with AnnotationInfos
                           with StandardDefinitions