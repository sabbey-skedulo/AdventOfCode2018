package solutions

import org.scalatest.freespec.AsyncFreeSpec

class Day2Test extends AsyncFreeSpec {
  "part 1" - {
    "should find the checksum" in {
      val testInput = List("abcdef", "bababc","abbcde", "abcccd", "aabcdd", "abcdee", "ababab" )
      assert(Day2.part1(testInput) == 12)
    }
  }
  "part 2" - {
    "should find the common matching box letters" in {
      val testInput = List(
        "abcde",
        "fghij",
        "klmno",
        "pqrst",
        "fguij",
        "axcye",
        "wvxyz",
      )
      assert(Day2.part2(testInput) == "fgij")
    }
  }
}
