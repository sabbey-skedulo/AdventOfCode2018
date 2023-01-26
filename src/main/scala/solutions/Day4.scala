package solutions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Day4 {
  def main(args: Array[String]): Unit = {
    val input = utils.Utils.readInputAsLines(4)
    val parsedInput = input.map(parseInput)
    println(part1(parsedInput))
    println(part2(parsedInput))
  }

  def parseInput(line: String): Event = {
    val dateAndEvent = line.tail.split("] ")
    val parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val date = LocalDateTime.parse(dateAndEvent.head, parser)
    dateAndEvent.last match {
      case "wakes up" => WakeEvent(date)
      case "falls asleep" => SleepEvent(date)
      case event =>
        val guardId = event.replace("Guard #", "").replace(" begins shift", "").toInt
        GuardStartEvent(date, guardId)
    }
  }

  def part1(input: List[Event]): Int = {
    val finalState: GuardState = findAllGuardSleepTimes(input)
    val longestGuard = finalState.guards.toList.maxBy(_._2.length)
    mostCommonValue(longestGuard) * longestGuard._1

  }

  private def findAllGuardSleepTimes(input: List[Event]) = {
    val finalState = input.sortBy(_.date).foldLeft(GuardState(0)) { (state, event) =>
      event match {
        case GuardStartEvent(_, guard) => GuardState(guard, None, state.guards)
        case SleepEvent(date) => state.copy(sleepTime = Some(date))
        case WakeEvent(date) =>
          state.sleepTime.map { sleepTime =>
            val minsAsleep = List.range(sleepTime.getMinute, date.getMinute)
            val prevAsleep = state.guards.getOrElse(state.guardId, List.empty)
            state.copy(guards = state.guards + (state.guardId -> (prevAsleep ++ minsAsleep)))
          }.getOrElse(state)
      }
    }
    finalState
  }

  def part2(input: List[Event]): Int = {
    val finalState: GuardState = findAllGuardSleepTimes(input)
    val longestMinForEachGuard = finalState.guards.toList.map(pair => {
      val mostCommon = mostCommonValue(pair)
      (pair._1, mostCommon, pair._2.count(_ == mostCommon))
    })
    val sleepiestGuardMin = longestMinForEachGuard.maxBy(_._3)
    sleepiestGuardMin._1 * sleepiestGuardMin._2
  }

  private def mostCommonValue(pair: (Int, List[Int])) = {
    pair._2.groupBy(identity).maxBy(_._2.size)._1
  }
}

sealed trait Event {
  def date: LocalDateTime
}

case class GuardStartEvent(date: LocalDateTime, guard: Int) extends Event

case class SleepEvent(date: LocalDateTime) extends Event

case class WakeEvent(date: LocalDateTime) extends Event

case class GuardState(guardId: Int, sleepTime: Option[LocalDateTime] = None, guards: Map[Int, List[Int]] = Map.empty)
