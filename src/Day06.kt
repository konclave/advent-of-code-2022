fun main() {
    fun solve1(input: List<String>, windowSize: Int): Int {
        val chars = input[0].toCharArray()
        val window = ArrayDeque<Char>(chars.take(windowSize - 1))
        chars.drop(windowSize - 1).forEachIndexed { idx, c ->
            window.addLast(c)
            if (window.distinct().size == window.size) {
                return idx + windowSize
            }
            window.removeFirst()
        }
        return 0
    }

    val input = readInput("Day06")

    println(solve1(input, 4))
    println(solve1(input, 14))
}
