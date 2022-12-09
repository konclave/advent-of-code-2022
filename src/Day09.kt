import kotlin.math.abs

class Dot {
    private var x: Int = 0
    private var y: Int = 0
    private var visited: MutableSet<String> = mutableSetOf("0,0")

    val visitedCount: Int
        get() {
            return visited.size
        }

    fun move(command: String): Pair<Int, Int> {
        when (command) {
            "R" -> {
                x += 1
            }
            "U" -> {
                y += 1
            }
            "L" -> {
                x -= 1
            }
            "D" -> {
                y -= 1
            }
        }
        return Pair(x, y)
    }

    fun follow(coords: Pair<Int, Int>): Pair<Int, Int> {
        val (destX, destY) = coords
        if (abs(destX - x) > 1 && y != destY || abs(destY - y) > 1 && destX != x)  {
            x += if (destX - x > 0) 1 else -1
            y += if (destY - y > 0) 1 else -1
        } else {
            if (abs(destX - x) > 1) {
                x += if ((destX - x) > 0) destX - x - 1 else destX - x + 1
            }
            if (abs(destY - y) > 1) {
                y += if ((destY - y) > 0) destY - y - 1 else destY - y + 1
            }
        }
        visited.add("$x,$y")
        return Pair(x, y)
    }
}

fun main() {
    fun solve1(input: List<String>): Int {
        val head = Dot()
        val tail = Dot()
        input.forEach {
            val (command, steps) = it.split(" ")
            repeat(steps.toInt()) {
                tail.follow(head.move(command))
            }
        }

        return tail.visitedCount
    }

    fun solve2(input: List<String>): Int {
        val rope: List<Dot> = List(9) { Dot() }
        val head = Dot()
        input.forEach {
            val (command, steps) = it.split(" ")
            repeat(steps.toInt()) {
                val headCoords = head.move(command)
                rope.fold(headCoords){ coords, dot ->
                    dot.follow(coords)
                }
            }
        }
        val tail = rope.last()
        return tail.visitedCount
    }

    val input = readInput("Day09")
    println(solve1(input))
    println(solve2(input))
}
