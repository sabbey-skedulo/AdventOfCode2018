package solutions

import scala.annotation.tailrec

object Day1 {
  def main(args: Array[String]) = {
    val input = utils.Utils.readInputAsLines(1)
    val parsedInput = input.map(f => f.toInt)
    println(part1(parsedInput))
    println(part2(parsedInput))
  }

  def part1(input: List[Int]): Int = input.sum

  @tailrec
  def part2(input: List[Int], initialState: Either[Int, ProgressState] = Right(ProgressState(0, List.empty))): Int = {
    val newState = input.foldLeft(initialState) {(state, next ) =>
      state match {
        case Left(doneState) => Left(doneState).withRight
        case Right(progressState) => {
          val newFrequency = next + progressState.frequency
          if (progressState.pastFrequencies.contains(newFrequency)) Left(newFrequency).withRight
          else Right(ProgressState(newFrequency, progressState.pastFrequencies :+ newFrequency))
        }
      }
    }
    newState match {
      case Left(doneState) => doneState
      case Right(_) => part2(input, newState)
    }
  }

}

case class ProgressState(frequency: Int, pastFrequencies: List[Int])