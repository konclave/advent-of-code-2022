fun main() {
   fun solve1(input: List<String>): Int {
       return input.fold(0) { acc, pair ->
           val (he, me) = pair.split(" ")
           val heInt = he.first().code
           val meInt = me.first().code
           val bonus = meInt - 87
           acc + bonus + when (heInt - meInt) {
               -23 -> 3
               -21, -24 -> 6
               else -> 0
           }
       }
   }

    fun solve2(input: List<String>): Int {
        return input.fold(0) { acc, pair ->
            val (he, me) = pair.split(" ")
            val heInt = he.first().code
            val meInt = me.first().code
            val bonus = (meInt - 88) * 3
            acc + bonus + when (heInt - 64) {
                1 -> {
                    when (meInt - 87) {
                        1 -> 3
                        2 -> 1
                        else -> 2
                    }
                }
                2 -> {
                    when (meInt - 87) {
                        1 -> 1
                        2 -> 2
                        else -> 3
                    }
                }
                else -> {
                    when (meInt - 87) {
                        1 -> 2
                        2 -> 3
                        else -> 1
                    }
                }
            }
        }
    }

    val input = readInput("Day02")
    println(solve1(input))
    println(solve2(input))
}
