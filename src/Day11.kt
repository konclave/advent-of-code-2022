import kotlin.collections.ArrayDeque

fun main() {
    class Monkey(monkey: List<String>, var worryDivider: Int? = null) {
        val id: Int
        val items: ArrayDeque<Long> = ArrayDeque()
        var actCount: Int = 0
        private val operator: String
        private val opArg: String
        val testNum: Int
        private val testTrueMonkeyId: Int
        private val testFalseMonkeyId: Int

        init {
            val (idStr, itemsStr, operationStr) = monkey
            val (testStr, testTrueStr, testFalseStr) = monkey.slice(3..5)
            this.id = Regex("Monkey (\\d+):").find(idStr)!!.groupValues[1].toInt()
            itemsStr
                .trim()
                .replace("Starting items: ", "")
                .split(", ")
                .forEach { this.items.addLast(it.toLong()) }
            this.testNum = testStr.trim().replace("Test: divisible by ", "").toInt()
            this.testTrueMonkeyId = testTrueStr.trim().replace("If true: throw to monkey ", "").toInt()
            this.testFalseMonkeyId = testFalseStr.trim().replace("If false: throw to monkey ", "").toInt()
            operationStr.trim().replace("Operation: new = ", "")
            val (_, operator, arg2) = Regex("([+*])\\s(.+)")
                .find(
                    operationStr
                        .trim()
                        .replace("Operation: new = old ", "")
                )!!
                .groupValues
            this.operator = operator
            this.opArg = arg2
        }

        fun act(): Pair<Long, Int> {
            val worryLevel = this.inspect()
            this.actCount += 1
            val next = this.getNextMonkeyId(worryLevel)
            return Pair(worryLevel, next)
        }

        private fun operation(old: Long): Long {
            return when (this.operator) {
                "+" -> {
                    if (this.opArg == "old") old + old else old + this.opArg.toInt()
                }

                "*" -> {
                    if (this.opArg == "old") old * old else old * this.opArg.toInt()
                }

                else -> throw Error("Unknown operator")
            }
        }

        private fun getNextMonkeyId(worryLevel: Long): Int {
            return if (worryLevel % this.testNum == 0L) this.testTrueMonkeyId else this.testFalseMonkeyId
        }

        private fun inspect(): Long {
            return if (this.worryDivider != null) this.operation(this.items.removeFirst()) % this.worryDivider!! else this.operation(this.items.removeFirst()) / 3
        }

        fun catchItem(worryLevel: Long) {
            this.items.addLast(worryLevel)
        }
    }

    fun solve1(input: List<String>): Long {
        val monkeys: List<Monkey> = input.chunked(7).map { Monkey(it) }

        for (i in 1..20) {
            monkeys.forEach { monkey ->
                while (monkey.items.size > 0) {
                    val (item, monkeyId) = monkey.act()
                    val destMonkey = monkeys.find { it.id == monkeyId }!!
                    destMonkey.catchItem(item)
                }
            }
        }

        return monkeys
            .sortedByDescending { it.actCount }
            .take(2)
            .fold(1) { acc, monkey -> acc * monkey.actCount }
    }

    fun solve2(input: List<String>): Long {
        val monkeys: List<Monkey> = input.chunked(7).map { Monkey(it, 1) }
        val divider = monkeys.map { it.testNum }.fold(1) { acc, it -> acc * it }
        monkeys.forEach { it.worryDivider = divider}

        repeat (10000) {
            monkeys.forEach { monkey ->
                while (monkey.items.size > 0) {
                    val (item, monkeyId) = monkey.act()
                    val destMonkey = monkeys.find { it.id == monkeyId }!!
                    destMonkey.catchItem(item)
                }
            }
        }

        return monkeys
            .sortedByDescending { it.actCount }
            .take(2)
            .fold(1L) { acc, monkey -> acc * monkey.actCount }
    }

    val input = readInput("Day11")
    println(solve1(input))
    println(solve2(input))
}
