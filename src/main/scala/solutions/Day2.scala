package solutions

object Day2 {
  def main(args: Array[String]) = {
    val input = utils.Utils.readInputAsLines(2)
    println(part1(input))
    println(part2(input))
  }

  def part1(input: List[String]): Int = {
    val twoReps = input.count(hasRepeatedLetter(_, 2))
    val threeReps = input.count(hasRepeatedLetter(_, 3))
    twoReps * threeReps
  }

  def hasRepeatedLetter(id: String, repetitions: Int): Boolean =
    id.exists(c => id.count(_ == c) == repetitions)


  def part2(input: List[String]): String = {
    input.map(id => input.flatMap(compareIds(_, id))).filterNot(_.isEmpty).head.head
  }

  def compareIds (id1: String, id2: String): Option[String] = {
    val matchingChars = id1.zip(id2).filter((chars) => chars._1 == chars._2)
    if (matchingChars.length != id1.length - 1) None else Some(matchingChars.map(_._1).mkString)
  }
}
