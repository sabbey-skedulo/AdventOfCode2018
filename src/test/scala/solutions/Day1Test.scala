package solutions

import org.scalatest.freespec.AsyncFreeSpec

class Day1Test extends AsyncFreeSpec {
  val testInput = List(1, -2, 3, 1)

  "part 1" - {
    "should find the total frequency" in {
      assert(Day1.part1(testInput) == 3)
    }
  }
  "part 2" - {
    "should find the first repeated frequency" in {
      assert(Day1.part2(testInput) == 2)
    }
  }
}
