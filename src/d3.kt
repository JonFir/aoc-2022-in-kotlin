fun main() {
    fun part1(input: String): Int = input.split("\n")
            .map {
                it.chunked(it.length / 2)
                        .map { it.toSet() }
                        .reduce({ acc, i -> acc.intersect(i) })
                        .first().code
            }
            .sumOf {
                if (it > 95) {
                    it - 96
                } else {
                    it - 38
                }
            }

    fun part2(input: String): Int = input.split("\n")
            .chunked(3)
            .map { chars: List<String> ->
                chars[0].toCharArray()
                        .first { c ->
                            chars.drop(1).fold(true) { ac, i -> i.contains(c) && ac }
                        }
            }
            .map { it.code }
            .sumOf {
                if (it > 95) {
                    it - 96
                } else {
                    it - 38
                }
            }
    val testResult1 = part1(readInputAsText("../input/d3/test"))
    check(testResult1 == 157)
    val realResult1 = part1(readInputAsText("../input/d3/real"))
    check(realResult1 == 7742)

    val testResult2 = part2(readInputAsText("../input/d3/test"))
    check(testResult2 == 70)

    val realResult2 = part2(readInputAsText("../input/d3/real"))
    check(realResult2 == 2276)
}