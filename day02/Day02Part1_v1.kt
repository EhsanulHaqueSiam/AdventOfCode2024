import java.io.File

fun isSafeReport(levels: List<Int>): Boolean {
    // Check if the levels are strictly increasing or decreasing
    val increasing = levels.zipWithNext().all { (a, b) -> b > a && b - a in 1..3 }
    val decreasing = levels.zipWithNext().all { (a, b) -> b < a && a - b in 1..3 }

    // A report is safe if it's either all increasing or all decreasing
    return increasing || decreasing
}

fun main() {
    val reports =
        File("input.txt")
            .readLines()
            .map { line -> line.split(" ").map { it.toInt() } }

    val safeReports = reports.filter(::isSafeReport)

    println("Number of safe reports: ${safeReports.size}")
}
