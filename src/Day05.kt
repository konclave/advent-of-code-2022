fun main() {
    fun solve1(input: List<String>): String {
        val movements = getMovements(input)
        val crateStacks = getCrateStacks(input)
        return applyMovements9000(movements, crateStacks)
    }

    fun solve2(input: List<String>): String {
        val movements = getMovements(input)
        val crateStacks = getCrateStacks(input)
        return applyMovements9001(movements, crateStacks)
    }

    val input = readInput("Day05")

    println(solve1(input))
    println(solve2(input))
}

fun getCrateStacks(lines: List<String>): List<ArrayDeque<String>> {
    val crates = lines
        .takeWhile { it.matches(Regex("\\s*\\[.+]\\s*")) }

    val cratesNumber = lines[crates.size]
        .split(" ")
        .last()
        .toInt()

    val initialStacks = List(cratesNumber) {  ArrayDeque<String>() }

    return crates
        .map { it
            .split("")
            .filterIndexed { i, _ -> i % 4 != 0 }
            .joinToString("")
        }
        .fold(initialStacks) {
                stacks, it ->
            it
                .chunked(3)
                .forEachIndexed { i, crate ->
                    val clean = crate.replace(Regex("[\\[\\]]"), "")
                    if (crate.trim() != "") stacks[i].addFirst(clean)}
            stacks
        }
}

fun getMovements(lines: List<String>): List<Triple<Int, Int, Int>> {
    val movementRegex = Regex("move\\s(\\d+)\\sfrom\\s(\\d+)\\sto\\s(\\d+)")
    return lines
        .dropWhile { !it.matches(movementRegex) }
        .map {
            val matched = movementRegex.find(it)
            if (matched != null) {
                val (_, number, from, to) = matched.groupValues
                Triple(number.toInt(), from.toInt() - 1, to.toInt() - 1)
            } else {
                Triple(0, 0, 0)
            }
        }
}

fun applyMovements9000(movements: List<Triple<Int, Int, Int>>, crates: List<ArrayDeque<String>>): String {
    movements.forEach {formula ->
        repeat(formula.first) {
            crates[formula.third].addLast(crates[formula.second].removeLast())
        }
    }
    return getTopCrates(crates)
}

fun applyMovements9001(movements: List<Triple<Int, Int, Int>>, crates: List<ArrayDeque<String>>): String {
    movements.forEach {formula ->
        val moving = List(formula.first) { crates[formula.second].removeLast() }
        for(i in 0 until formula.first) {
            crates[formula.third].addLast(moving[moving.size - i - 1])
        }
    }
    return getTopCrates(crates)
}

fun getTopCrates(crates: List<ArrayDeque<String>>): String {
    return crates.fold("") { acc, stack -> acc + stack.last() }
}