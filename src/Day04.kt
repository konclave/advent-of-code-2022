fun main() {
    fun solve1(input: List<String>): Int {
      return input.filter {
          val (fstStart, fstEnd, sndStart, sndEnd) = it.split(Regex("[-,]")).map{ it.toInt() }
          sndStart >= fstStart && sndEnd <= fstEnd || fstStart >= sndStart && fstEnd <= sndEnd
      }.size
    }

    fun solve2(input: List<String>): Int {
        return input.filter {
            val (fstStart, fstEnd, sndStart, sndEnd) = it.split(Regex("[-,]")).map{ it.toInt() }
            sndStart in fstStart..fstEnd || fstStart in sndStart..sndEnd
        }.size
    }

    val input = readInput("Day04")
    println(solve1(input))
    println(solve2(input))
}
