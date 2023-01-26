package solutions

import org.scalatest.freespec.AsyncFreeSpec
import utils.Point

import java.time.LocalDateTime
import java.util.Date

class Day4Test extends AsyncFreeSpec {
  "parseInput" - {
    "should parse a guard start event" in {
      val event = Day4.parseInput("[1518-07-16 00:00] Guard #3209 begins shift")
      assert(event == GuardStartEvent(LocalDateTime.of(1518, 7, 16, 0, 0), 3209))
    }
    "should parse a sleep event" in {
      val event = Day4.parseInput("[1518-06-13 00:02] falls asleep")
      assert(event == SleepEvent(LocalDateTime.of(1518, 6, 13, 0, 2)))
    }
    "should parse a wake event" in {
      val event = Day4.parseInput("[1518-06-13 00:02] wakes up")
      assert(event == WakeEvent(LocalDateTime.of(1518, 6, 13, 0, 2)))
    }
  }
  private val testInput = List(
    "[1518-11-01 00:00] Guard #10 begins shift",
    "[1518-11-01 00:05] falls asleep",
    "[1518-11-01 00:25] wakes up",
    "[1518-11-01 00:30] falls asleep",
    "[1518-11-01 00:55] wakes up",
    "[1518-11-01 23:58] Guard #99 begins shift",
    "[1518-11-02 00:40] falls asleep",
    "[1518-11-02 00:50] wakes up",
    "[1518-11-03 00:05] Guard #10 begins shift",
    "[1518-11-03 00:24] falls asleep",
    "[1518-11-03 00:29] wakes up",
    "[1518-11-04 00:02] Guard #99 begins shift",
    "[1518-11-04 00:36] falls asleep",
    "[1518-11-04 00:46] wakes up",
    "[1518-11-05 00:03] Guard #99 begins shift",
    "[1518-11-05 00:45] falls asleep",
    "[1518-11-05 00:55] wakes up",
  ).map(Day4.parseInput)

  "part 1" - {
    "should find most sleepy guard multiplied by their most slep min" in {
      assert(Day4.part1(testInput) == 240)
    }
  }
    "part 2" - {
      "should guard asleep on the same minute the most" in {
        assert(Day4.part2(testInput) == 4455)
      }
    }
}
