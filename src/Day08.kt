fun main() {
    fun solve1(trees: List<String>): Int {
        return trees.foldIndexed(0) {
            rowIdx, total, row ->
                if (rowIdx == 0 || rowIdx == trees.size - 1) {
                    total + row.length
                } else {
                    total + row.foldIndexed(0) { colIdx, countCol, char ->
                        if (colIdx == 0 || colIdx == trees[rowIdx].length - 1) {
                            countCol + 1
                        } else {
                            var isVisibleTop = true
                            var isVisibleBottom = true
                            var isVisibleLeft = true
                            var isVisibleRight = true
                            for(idx in (colIdx - 1)downTo 0) {
                                if (row[idx] >= char) {
                                    isVisibleLeft = false
                                    break
                                }
                            }
                            for(idx in (colIdx + 1) until row.length) {
                                if (row[idx] >= char) {
                                    isVisibleRight = false
                                    break
                                }
                            }
                            for(idx in rowIdx - 1 downTo 0 ) {
                                if (trees[idx][colIdx] >= char) {
                                    isVisibleTop = false
                                    break
                                }
                            }
                            for(idx in (rowIdx + 1) until trees.size) {
                                if (trees[idx][colIdx] >= char) {
                                    isVisibleBottom = false
                                    break
                                }
                            }
                            countCol + if (isVisibleLeft || isVisibleRight || isVisibleTop || isVisibleBottom) 1 else 0
                        }
                    }
                }
        }
    }

    fun solve2(trees: List<String>): Int {
        return trees.foldIndexed(0) {
                rowIdx, totalScore, row ->
            val rowScore = row.foldIndexed(0) { colIdx, colScore, char ->
                var topScore = 0
                var bottomScore = 0
                var leftScore = 0
                var rightScore = 0
                if (colIdx > 0) {
                    leftScore = 1
                    for (idx in (colIdx - 1) downTo 0) {
                        if (row[idx] >= char) {
                            leftScore = colIdx - idx
                            break
                        }
                        leftScore = colIdx
                    }
                }
                if (colIdx < row.length - 1) {
                    rightScore = 1
                    for (idx in (colIdx + 1) until row.length) {
                        if (row[idx] >= char) {
                            rightScore = idx - colIdx
                            break
                        }
                        rightScore = row.length - colIdx - 1
                    }
                }
                if (rowIdx > 0) {
                    topScore = 1
                    for (idx in rowIdx - 1 downTo 0) {
                        if (trees[idx][colIdx] >= char) {
                            topScore = rowIdx - idx
                            break
                        }
                        topScore = rowIdx
                    }
                }
                if (rowIdx < trees.size - 1) {
                    bottomScore = 1
                    for (idx in (rowIdx + 1) until trees.size) {
                        if (trees[idx][colIdx] >= char) {
                            bottomScore = idx - rowIdx
                            break
                        }
                        bottomScore = trees.size - rowIdx - 1
                    }
                }
                colScore.coerceAtLeast(topScore * rightScore * bottomScore * leftScore)
            }
            rowScore.coerceAtLeast(totalScore)
        }
    }

    val input = readInput("Day08")
    println(solve1(input))
    println(solve2(input))
}
