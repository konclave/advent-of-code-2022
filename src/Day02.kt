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
	        .sortedDescending()
	        .take(3)
	        .sum()
    }

    val input = readInput("Day01")
    val solution = solve(input)
    println(solution)
}
