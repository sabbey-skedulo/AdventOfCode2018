package solutions

import org.scalatest.freespec.AsyncFreeSpec
import utils.Point

class Day3Test extends AsyncFreeSpec {
  "parseInput" - {
    "should parse into start and size" in {
      val square = Day3.parseInput("#3 @ 3,5: 2x4")
      assert(square.id == 3)
      assert(square.start == Point(3, 5))
      assert(square.size == Point(2, 4))
    }
  }
  val testInput = List(
    Square(1, Point(1, 3), Point(4, 4)),
    Square(2, Point(3, 1), Point(4, 4)),
    Square(3, Point(5, 5), Point(2, 2)),
  )

  "part 1" - {
    "should find overlapping" in {
      assert(Day3.part1(testInput) == 4)
    }
  }
  "part 2" - {
    "should find none overlapping id" in {
      assert(Day3.part2(testInput).contains(3))
    }
  }
}
