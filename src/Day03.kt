fun main() {
    fun pointCount(code: Int): Int {
        return code - if (code < 97) 38 else 96
    }

    fun solve1(input: List<String>): Int {
        return input.sumOf {
            val (cmp1, cmp2) = it.chunked(it.length / 2)
            var res = 0
            for (c in cmp1) {
                if (cmp2.contains(c)) {
                    res = pointCount(c.code)
                }
            }
            res
        }
    }

    fun solve2(input: List<String>): Int {
        return input.chunked(3).sumOf {
            val (bp1, bp2, bp3) = it
            var res = 0
            for (c in bp1) {
                if (bp2.contains(c) && bp3.contains(c)) {
                    res = pointCount(c.code)
                    break
                }
            }
            res
        }
    }

    val input = readInput("Day03")
    println(solve1(input))
    println(solve2(input))
}
