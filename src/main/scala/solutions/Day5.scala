package solutions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Day5 {
  def main(args: Array[String]): Unit = {
    val input = utils.Utils.readInputAsLines(5).head
    println(part1(input))
    println(part2(input))
  }


  def part1(input: String): Int = {
    val firstExplosion = findExplosion(input)
    firstExplosion.map(ex => part1(input.replace(ex, ""))).getOrElse(input.length)
  }

  def isAnExplosion(chars: Array[Char]): Boolean = chars.forall(_.toLower == chars.head.toLower) && !chars.forall(_ == chars.head)

  def findExplosion(input: String): Option[String] = {
    val chunks = input.toCharArray.sliding(2)
    chunks.find(isAnExplosion).map(_.mkString)
  }

  def part2(input: String): Int = {
    val allTypes = input.map(ta => ta.toLower.toString).toSet
    allTypes.map(c => part1(input.replace(c, "").replace(c.toUpperCase(), ""))).min
  }
}