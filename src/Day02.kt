fun main() {
   fun solve(input: List<String>): Int {
       return input.fold(0) { acc, pair ->
           val (he, me) = pair.split(" ")
           val heInt = he.first().code
           val meInt = me.first().code
           val bonus = meInt - 87
           when (heInt - meInt) {
               -23 -> acc + 3 + bonus
               -21, -24 -> acc + 6 + bonus
               else -> acc + bonus
           }
       }
   }

    val input = readInput("Day02")
    val solution = solve(input)
    println(solution)
}
