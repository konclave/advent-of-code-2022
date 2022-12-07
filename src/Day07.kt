class Dir(
    val name: String,
) {
    var children: MutableList<Dir> = mutableListOf()
    var parent: Dir? = null
    var value: Int = 0
        set(v) {
            if (this.parent != null) {
                this.parent!!.value = v
            }
            field += v
        }

    constructor(name: String, parent: Dir) : this(name) {
        parent.children.add(this)
        this.parent = parent
    }
}

fun createTree(input: List<String>): Dir {
    var node = Dir("/")
    val root = node
    input.forEach {
        val parsed = it.split(" ")
        if (parsed.size == 3) {
            val (_, _, path) = parsed
            when (path) {
                "/" -> {}
                ".." -> {
                    node = node.parent!!
                }
                else -> {
                    node = node.children.find { child -> child.name == path }!!
                }
            }
        } else {
            val (prompt, path) = parsed
            when (prompt) {
                "$" -> {}
                "dir" -> {
                    Dir(path, node)
                }
                else -> {
                    node.value = prompt.toInt()
                }
            }
        }
    }
    return root
}

fun solve1(root: Dir, sizeLimit: Int): Int {
    val stack: MutableList<Dir> = mutableListOf()
    stack.add(root)
    var total = 0
    while(stack.size > 0) {
        val current = stack.removeLast()
        if (current.value <= sizeLimit) {
            total += current.value
        }

        if (current.children.size > 0) {
            current.children.forEach { stack.add(it) }
        }
    }

    return total
}

fun solve2(root: Dir, total: Int, need: Int): Int {
    val stack: MutableList<Dir> = mutableListOf()
    val available = total - root.value
    stack.add(root)
    var remove: Dir = root
    while(stack.size > 0) {
        val current = stack.removeLast()
        if (available + current.value >= need && current.value < remove.value) {
            remove = current
        }

        if (current.children.size > 0) {
            current.children.forEach { stack.add(it) }
        }
    }
    return remove.value
}

fun main() {
    val input = readInput("Day07")
    val root = createTree(input)
    println(solve1(root, 100000))
    println(solve2(root, 70000000, 30000000))
}
