interface D11Operation {
    fun calculate(old: Long): Long

    companion object Factory {
        fun parse(rawOperation: String): D11Operation {
            val components = rawOperation.split(" = ")[1].split(" ")
            val secondOperand = components[2].toLongOrNull()
            return when (components[1]) {
                "+" -> D11OperationSum(secondOperand)
                "*" -> D11OperationMultiple(secondOperand)
                else -> throw Exception()
            }
        }
    }
}

class D11OperationSum(private val operand: Long? = null) : D11Operation {
    override fun calculate(old: Long): Long {
        return old + (operand ?: old)
    }
}

class D11OperationMultiple(private val operand: Long? = null) : D11Operation {
    override fun calculate(old: Long): Long {
        return old * (operand ?: old)
    }
}

class D11Monkey(
        val items: MutableList<Long> = mutableListOf(),
        val operation: D11Operation,
        val divisibleBy: Long,
        val firstTarget: Int,
        val secondTarget: Int
) {
    var inspectedItemsCount = 0
    companion object Factory {
        fun parse(rawMonkey: String): D11Monkey {
            val monkeyComponents = rawMonkey.split("\n")
            return D11Monkey(
                    items = monkeyComponents[1].split(": ")[1].split(", ").map { it.toLong() }.toMutableList(),
                    operation = D11Operation.parse(monkeyComponents[2]),
                    divisibleBy = monkeyComponents[3].split("by ")[1].toLong(),
                    firstTarget = monkeyComponents[4].split("monkey ")[1].toInt(),
                    secondTarget = monkeyComponents[5].split("monkey ")[1].toInt(),
            )
        }
    }
}

fun main() {

    fun part1(input: String, iterations: Int, worryLevelDivider: Int? = 0): Long {
        val monkeys = input.split("\n\n").map { D11Monkey.parse(it) }
        val mod = monkeys.fold(1L) { acc, i -> acc * i.divisibleBy }

        for (iteration in 0 until iterations) {
            for (monkeyIndex in monkeys.indices) {
                val currentMonkey = monkeys[monkeyIndex]
                for (item in currentMonkey.items) {
                    currentMonkey.inspectedItemsCount += 1
                    val worryLevel = if (worryLevelDivider != null) {
                        currentMonkey.operation.calculate(item) / worryLevelDivider
                    } else {
                        currentMonkey.operation.calculate(item) % mod
                    }
                    val nextMonkey = if (worryLevel % currentMonkey.divisibleBy == 0L) {
                        currentMonkey.firstTarget
                    } else {
                        currentMonkey.secondTarget
                    }
                    monkeys[nextMonkey].items.add(worryLevel)
                }
                currentMonkey.items.clear()
            }
        }
        return monkeys.map { it.inspectedItemsCount.toLong() }.sortedDescending().take(2).reduce { acc, i -> acc * i }
    }


    val testResult1 = part1(readInputAsText("../input/d11/test"), 20, 3)
    check(testResult1 == 10605L)
    val realResult1 = part1(readInputAsText("../input/d11/real"), 20, 3)
    check(realResult1 == 66802L)

    val testResult2 = part1(readInputAsText("../input/d11/test"), 10000, null)
    check(testResult2 == 2713310158)

    val realResult2 = part1(readInputAsText("../input/d11/real"), 10000, null)
    check(realResult2 == 21800916620)
}