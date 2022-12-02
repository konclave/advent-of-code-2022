fun main() {
    fun solve(input: List<String>): Int {
        return input
            .joinToString("\n")
            .split("\n\n")
            .map{ chunk ->
                chunk
                    .split("\n")
                    .map{ it.toInt() }
                    .sum()
            }
            .max()
    }

    val input = readInput("Day01")
    val solution = solve(input)
    println(solution)
}