sealed class D10Command() {
    companion object Factory {
        fun parse(rawCommand: String) : D10Command {
            val components = rawCommand.split(" ")
            return when (components[0]) {
                "noop" -> Noop
                "addx" -> Add(components[1].toInt())
                else -> throw Exception()
            }

        }

    }
}

object Noop : D10Command()
class Add(val x: Int): D10Command()

data class D10Device(val screenSize: Int) {

    private val cyclesHistory: MutableList<Int> = mutableListOf(1)
    private val pixels: MutableList<String> = mutableListOf()

    fun apply(command: D10Command) {
        when (command) {
            is Noop -> {
                drawPixel()
                cyclesHistory.add(cyclesHistory.last())
            }
            is Add -> {
                drawPixel()
                cyclesHistory.add(cyclesHistory.last())
                drawPixel()
                cyclesHistory.add(cyclesHistory.last() + command.x)
            }
        }
    }

    fun testSignal(): Int {
        return (20..220 step screenSize).sumOf { cyclesHistory[it - 1] * it }
    }

    fun drawMessage() {
        pixels.chunked(screenSize).map { it.joinToString("") } .forEach { println(it) }
    }

    private fun drawPixel() {
        val pixel = (cyclesHistory.size - 1) % screenSize
        val spriteCenter = cyclesHistory.last()
        val sprite = spriteCenter - 1 .. spriteCenter + 1
        val value = if (sprite.contains(pixel)) {
            "⚪️"
        } else {
            "⚫️"
        }
        pixels.add(value)
    }

}

fun main() {

    fun part1(input: List<String>): Int {
        val device = D10Device(40)

        for (rawCommand in input) {
            val command = D10Command.parse(rawCommand)
            device.apply(command)
        }

        return device.testSignal()
    }

    fun part2(input: List<String>) {
        val device = D10Device(40)

        for (rawCommand in input) {
            val command = D10Command.parse(rawCommand)
            device.apply(command)
        }

        device.drawMessage()
    }

    val testResult1 = part1(readInput("../input/d10/test"))
    check(testResult1 == 13140)
    val realResult1 = part1(readInput("../input/d10/real"))
    check(realResult1 == 16880)

    part2(readInput("../input/d10/test"))
    println("\n\n\n")
    part2(readInput("../input/d10/real"))
}