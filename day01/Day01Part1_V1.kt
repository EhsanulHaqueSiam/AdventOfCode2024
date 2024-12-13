import java.io.File
import kotlin.math.abs

fun readInput(filename: String): List<String> = File(filename).readLines()

fun main() {
    val lines = readInput("input.txt")
    val (left, right) =
        lines
            .map { line ->
                val first = line.substringBefore(" ").toInt()
                val second = line.substringAfterLast(" ").toInt()
                first to second
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
