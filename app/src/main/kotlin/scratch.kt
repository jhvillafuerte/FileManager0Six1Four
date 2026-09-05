fun main() {
    val arr = intArrayOf(1, 2, 3)
    println(solution(arr))
}

fun solution(a: IntArray): Int {
    val set = a.toHashSet()
    var i = 1
    while (true) {
        if (!set.contains(i)) return i
        i++
    }
}
