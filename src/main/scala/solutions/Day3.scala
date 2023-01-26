package solutions

import utils.Point

object Day3 {
  def main(args: Array[String]) = {
    val input = utils.Utils.readInputAsLines(3)
    val parsedInput = input.map(parseInput)
    println(part1(parsedInput))
    println(part2(parsedInput))
  }

  def parseInput(line: String): Square = {
    val idAndSize = line.split(" @ ")
    val id = idAndSize.head.tail.toInt
    val startAndSize = idAndSize.last.split(": ")
    Square(id, toPoint(startAndSize.head, ','), toPoint(startAndSize.last, 'x'))
  }

  def toPoint(str: String, delimiter: Char): Point = {
    val xAndY = str.split(delimiter).map(_.toInt)
    Point(xAndY.head, xAndY.last)
  }

  def part1(input: List[Square]): Int = {
    val finalState = input.foldLeft(State(Set.empty, Set.empty)) { (state, next) =>
      val coveredPoints: _root_.scala.collection.immutable.Set[_root_.utils.Point] = getAllPoints(next)
      val newDuplicates = state.duplicates.union( state.allPoints.intersect(coveredPoints))
      State(newDuplicates, state.allPoints.union(coveredPoints))
    }
    finalState.duplicates.size
  }


  private def getAllPoints(next: Square) = {
    val xPoints = List.range(next.start.x, next.start.x + next.size.x)
    val yPoints = List.range(next.start.y, next.start.y + next.size.y)
    val coveredPoints = xPoints.flatMap(x => yPoints.map(y => Point(x, y))).toSet
    coveredPoints
  }

  def hasOverlap(square: Square, otherSquare: Square): Boolean = {
    getAllPoints(square).intersect(getAllPoints(otherSquare)).nonEmpty
  }

  def part2(input: List[Square]): Option[Int] = {
    input.find(square => input.count(hasOverlap(_, square)) <= 1).map(_.id)
  }
}

case class Square(id: Int, start: Point, size: Point)

case class State(duplicates: Set[Point], allPoints: Set[Point])