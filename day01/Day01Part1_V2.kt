
import java.io.File
import kotlin.math.abs

fun readInput(filename: String): List<String> = File(filename).readLines()

fun main() {
    val lines = readInput("input.txt")
    val (left, right) =
        lines
            .map { line ->
                line.split(Regex("\\s+")).let {
                    require(it.size == 2)
                    it[0].toLong() to it[1].toLong()
                }
            }.unzip()
    val result =
        left
            .sorted()
            .zip(right.sorted())
            .map { (first, second) ->
                abs(first - second)
            }.sum()

    println("Result: $result")
}
