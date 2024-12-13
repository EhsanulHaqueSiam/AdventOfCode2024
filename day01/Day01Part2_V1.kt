import java.io.File

fun readInput(filename: String): List<String> = File(filename).readLines()

fun main() {
    // Read the input file
    val lines = readInput("input.txt")

    // Step 1: Parse the input and split into pairs
    val (left, right) =
        lines
            .map { line ->
                // Split each line into two numbers and convert to Long
                line.split("""\s+""".toRegex()).let {
                    require(it.size == 2)
                    it[0].toLong() to it[1].toLong()
                }
            }.unzip() // Split the pairs into two separate lists: left and right

    /*
        Example:
        Input:
        1 2
        2 3
        3 2
        4 2

        After unzip():
        left = [1, 2, 3, 4]
        right = [2, 3, 2, 2]
     */

    // Step 2: Use groupBy to group elements in 'right' by value and count occurrences
    val groupBy: Map<Long, Int> =
        right
            .groupBy { it }
            .mapValues { it.value.size }
    /*
        groupBy groups elements into lists based on value:
        {
            2: [2, 2, 2],
            3: [3]
        }
        mapValues replaces the lists with their sizes (counts):
        groupBy = {
            2: 3,
            3: 1
        }
     */

    // Step 3: Use groupingBy and eachCount for a concise count of occurrences
    val frequency: Map<Long, Int> = right.groupingBy { it }.eachCount()
    /*
        frequency directly counts occurrences:
        frequency = {
            2: 3,
            3: 1
        }
     */

    // Note: Both groupBy and groupingBy achieve the same result here, but groupingBy is more efficient.

    // Step 4: Use fold to compute the result based on 'left' and 'frequency'
    left
        .fold(0L) { acc, num ->
            // Multiply each element in 'left' by its frequency in 'right'
            acc + num * frequency.getOrDefault(num, 0)
        }.also { println(it) } // Print the result

    /*
        Example calculation:
        left = [1, 2, 3, 4]
        frequency = {
            2: 3,
            3: 1
        }

        acc = 0L
        acc = acc + 1 * frequency.getOrDefault(1, 0) // 1 * 0 = 0
        acc = acc + 2 * frequency.getOrDefault(2, 0) // 2 * 3 = 6
        acc = acc + 3 * frequency.getOrDefault(3, 0) // 3 * 1 = 3
        acc = acc + 4 * frequency.getOrDefault(4, 0) // 4 * 0 = 0
        Total = 6 + 3 = 9

        Final output: 9
     */
}
