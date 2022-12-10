fun main() {
    fun solve1(input: List<String>): Int {
        var total = 0
        val ticks = listOf(20, 60, 100, 140, 180, 220)
        input.flatMap { it.split(" ") }.foldIndexed(1) { i, acc, str ->
            var res = acc
            when (str) {
                "noop", "addx" -> {}
                else -> {
                    res = acc + str.toInt()
                }
            }
            if (ticks.contains(i + 1)) {
                val strength = (i + 1) * acc
                total += strength
            }
            res
        }
        return total
    }

    fun solve2(input: List<String>) {
        val screen: MutableList<String> = mutableListOf()
        input.flatMap { it.split(" ") }.foldIndexed(1) { i, acc, str ->
            var res = acc
            when (str) {
                "noop", "addx" -> {}
                else -> {
                    res = acc + str.toInt()
                }
            }
            val isBeamInPixel = i.mod(40) <= acc + 1 && i.mod(40) >= acc - 1
            screen.add(if (isBeamInPixel) "#" else ".")
            res
        }
        screen.chunked(40).map { it.joinToString("") }.forEach { println(it) }
    }

    val input = readInput("Day10")
    println(solve1(input))
    println(solve2(input))
}
