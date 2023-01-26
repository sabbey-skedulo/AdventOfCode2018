package solutions

import org.scalatest.freespec.AsyncFreeSpec

import java.time.LocalDateTime

class Day5Test extends AsyncFreeSpec {
  private val testInput = "dabAcCaCBAcCcaDA"
  "part 1" - {
    "should find the length of the simplified polymer" in {
      assert(Day5.part1(testInput) == 10)
    }
  }
    "part 2" - {
      "should find the smallest length of the simplified polymer with one type removed" in {
        assert(Day5.part2(testInput) == 4)
      }
    }
}
