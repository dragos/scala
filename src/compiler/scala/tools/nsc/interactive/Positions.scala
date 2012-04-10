package scala.tools.nsc.interactive

import scala.tools.nsc.util.SourceFile
import scala.reflect.generic.interactive

trait Positions extends interactive.Positions {
  type Position = scala.tools.nsc.util.Position
  
  def rangePos(source: SourceFile, start: Int, point: Int, end: Int): Position
}