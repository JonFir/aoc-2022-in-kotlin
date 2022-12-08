fun part1(input: List<String>): Int = input.flatMap { it.trim().split("").drop(1).dropLast(1).map { it.toInt() } }
        .chunked(input.size * input.size).map { matrix ->
            matrix.foldIndexed(0) { index, acc, item ->
                if (index / input.size == 0 || index / input.size == input.size - 1 || index % input.size == 0 || index % input.size == input.size - 1) {
                    acc + 1
                } else if ((index / input.size * input.size until index).map { matrix[it] }.any { it >= item  }.not()
                        || (index + 1 until index / input.size * input.size + input.size).map { matrix[it] }.any { it >= item  }.not()
                        || (index - index / input.size * input.size until index step input.size).map { matrix[it] }.any { it >= item  }.not()
                        || (index + input.size .. (input.size - 1 - index / input.size) * input.size + index % input.size + index step input.size).map { matrix[it] }.any { it >= item  }.not() ) {
                    acc + 1
                } else {
                    acc
                }
            }
        }.first()

fun part2(input: List<String>): Int = input.flatMap { it.trim().split("").drop(1).dropLast(1).map { it.toInt() } }
        .chunked(input.size * input.size).map { matrix ->
            matrix.mapIndexed { index, item ->
                (index / input.size * input.size until index).reversed().map { matrix[it] }.foldIndexed(listOf<Int>()){ index, acc, e ->  if ((e < item && (acc.lastOrNull() ?: 0) < item) || ((acc.lastOrNull() ?: 0) < item && index == acc.size)) { acc + e } else { acc } }.count() *
                        (index + 1 until index / input.size * input.size + input.size).map { matrix[it] }.foldIndexed(listOf<Int>()){ index, acc, e ->  if ((e < item && (acc.lastOrNull() ?: 0) < item) || ((acc.lastOrNull() ?: 0) < item && index == acc.size)) { acc + e } else { acc } }.count() *
                        (index - index / input.size * input.size until index step input.size).reversed().map { matrix[it] }.foldIndexed(listOf<Int>()){ index, acc, e ->  if ((e < item && (acc.lastOrNull() ?: 0) < item) || ((acc.lastOrNull() ?: 0) < item && index == acc.size)) { acc + e } else { acc } }.count() *
                        (index + input.size..(input.size - 1 - index / input.size) * input.size + index % input.size + index step input.size).map { matrix[it] }.foldIndexed(listOf<Int>()){ index, acc, e ->  if ((e < item && (acc.lastOrNull() ?: 0) < item) || ((acc.lastOrNull() ?: 0) < item && index == acc.size)) { acc + e } else { acc } }.count()
            }.max()
        }.first()

fun main() {
    val testResult1 = part1(readInput("../input/d8/test"))
    check(testResult1 == 21)
    val realResult1 = part1(readInput("../input/d8/real"))
    check(realResult1 == 1859)

    val testResult2 = part2(readInput("../input/d8/test"))
    check(testResult2 == 8)
    val realResult2 = part2(readInput("../input/d8/real"))
    check(realResult2 == 332640)
}