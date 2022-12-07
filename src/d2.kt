fun main() {
    fun part1(input: String): Int = input.split("\n")
            .map {
                when (it) {
                    "A X" -> 4
                    "A Y" -> 8
                    "A Z" -> 3
                    "B X" -> 1
                    "B Y" -> 5
                    "B Z" -> 9
                    "C X" -> 7
                    "C Y" -> 2
                    "C Z" -> 6
                    else -> throw Exception()
                }
            }
            .reduce { acc, i -> i + acc }
    fun part2(input: String): Int = input.split("\n")
            .map {
                when (it) {
                    "A X" -> 3
                    "A Y" -> 4
                    "A Z" -> 8
                    "B X" -> 1
                    "B Y" -> 5
                    "B Z" -> 9
                    "C X" -> 2
                    "C Y" -> 6
                    "C Z" -> 7
                    else -> throw Exception()
                }
            }
            .reduce { acc, i -> i + acc }

    val testResult1 = part1(readInputAsText("../input/d2/test"))
    check(testResult1 == 15)
    val realResult1 = part1(readInputAsText("../input/d2/real"))
    check(realResult1 == 11150)

    val testResult2 = part2(readInputAsText("../input/d2/test"))
    check(testResult2 == 12)

    val realResult2 = part2(readInputAsText("../input/d2/real"))
    check(realResult2 == 8295)
}