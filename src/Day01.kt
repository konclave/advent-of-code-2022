fun main() {
    fun part1(input: List<String>): Int {
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

    fun part2(input: List<String>): Int {
        return input
            .joinToString("\n")
            .split("\n\n")
            .map{ chunk ->
                chunk
                    .split("\n")
                    .map{ it.toInt() }
                    .sum()
            }
            .sortedDescending()
            .take(3)
            .sum()
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}