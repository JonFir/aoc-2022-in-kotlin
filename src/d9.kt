import kotlin.math.absoluteValue

data class D9Command(val direction: String, val distance: Int) {
    companion object Factory {
        fun parse(rawCommand: String): D9Command {
            val components = rawCommand.split(" ")
            return D9Command(components[0], components[1].toInt())
        }
    }
}

data class D9Position(var y: Int, var x: Int) {

    fun move(direction: String) {
        when (direction) {
            "U" -> y += 1
            "D" -> y -= 1
            "L" -> x -= 1
            "R" -> x += 1
            else -> throw Exception()
        }
    }

    fun follow(position: D9Position) {
        val yDif = position.y - y
        val xDif = position.x - x
        val dif = yDif.absoluteValue + xDif.absoluteValue

        if (yDif.absoluteValue > 1 || dif > 2) {
            y += yDif / yDif.absoluteValue
        }

        if (xDif.absoluteValue > 1 || dif > 2) {
            x += xDif / xDif.absoluteValue
        }
    }

    constructor(): this(0, 0)

}

fun main() {

    fun part(input: List<String>, trainSize: Int): Int {
        val train = List(trainSize) { D9Position() }
        val positionHistory = mutableSetOf<D9Position>()

        for (rawCommand in input) {
            val command = D9Command.parse(rawCommand)

            for (i in 0 until command.distance) {
                train[0].move(command.direction)

                for (index in 1 until train.size) {
                    train[index].follow(train[index - 1])
                }

                positionHistory.add(train.last().copy())
            }

        }

        return positionHistory.size
    }

    val testResult1 = part(readInput("../input/d9/test"), 2)
    check(testResult1 == 13)
    val realResult1 = part(readInput("../input/d9/real"), 2)
    check(realResult1 == 6067)

    val testResult2 = part(readInput("../input/d9/test"), 10)
    check(testResult2 == 1)
    val realResult2 = part(readInput("../input/d9/real"), 10)
    check(realResult2 == 332640)
}