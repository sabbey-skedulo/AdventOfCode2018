package utils
import scala.io.Source

object Utils {
  def readInputAsLines(day: Int): List[String] = Source.fromResource(s"day$day.txt").getLines().toList
}


case class Point(x: Int, y: Int)