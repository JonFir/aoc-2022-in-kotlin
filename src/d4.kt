fun main() {
    fun part1(input: String): Int = input.split("\n")
            .map {
                it.split(",").map {
                    it.split("-")
                            .map {  it.toInt() }
                            .chunked(2)
                            .map {
                                (it[0]..it[1]).toSet()
                            }
                            .first()
                }
            }
            .count {
                it[0].containsAll(it[1]) || it[1].containsAll(it[0])
            }

    fun part2(input: String): Int = input.split("\n")
            .map {
                it.split(",").map {
                    it.split("-")
                            .map {  it.toInt() }
                            .chunked(2)
                            .map {
                                (it[0]..it[1]).toSet()
                            }
                            .first()
                }
            }
            .count {
                it[0].intersect(it[1]).isNotEmpty()
            }

    val testResult1 = part1(readInputAsText("../input/d4/test"))
    check(testResult1 == 2)
    val realResult1 = part1(readInputAsText("../input/d4/real"))
    check(realResult1 == 475)

    val testResult2 = part2(readInputAsText("../input/d4/test"))
    check(testResult2 == 4)
    val realResult2 = part2(readInputAsText("../input/d4/real"))
    check(realResult2 == 825)
}