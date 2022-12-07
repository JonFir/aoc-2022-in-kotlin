
fun main() {
    fun part1(input: String): Int = input.split("\n\n").map { it.split("\n").sumOf { it.toInt() } }.maxOf { it }
    fun part2(input: String): Int = input.split("\n\n").map { it.split("\n").sumOf { it.toInt() } }.sortedDescending().take(3).sum()

    // test if implementation meets criteria from the description, like:
    val testResult1 = part1(readInputAsText("../input/d1/test"))
    check(testResult1 == 24000)
    val realResult1 = part1(readInputAsText("../input/d1/real"))
    check(realResult1 == 70698)

    val testResult2 = part2(readInputAsText("../input/d1/test"))
    check(testResult2 == 45000)

    val realResult2 = part2(readInputAsText("../input/d1/real"))
    check(realResult2 == 206643)
}
