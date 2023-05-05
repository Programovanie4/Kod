import java.io.File

//fun next(a : List<CharArray>) : : List<CharArray> {
//
//
//
//}
data class Con(val l : Set<Pair<Int, Int>>, val r : Set<Pair<Int, Int>>, val u : Set<Pair<Int, Int>>, val d : Set<Pair<Int, Int>>) {

}

fun main() {
    var inp = File(
        "in/day24.txt"
        ).readLines().map{it.toCharArray()}
    val start = Pair(0,1)
    var r = 0
    var right = mutableListOf<Pair<Int, Int>>()
    var up = mutableListOf<Pair<Int, Int>>()
    var down = mutableListOf<Pair<Int, Int>>()
    var left = mutableListOf<Pair<Int, Int>>()

    fun mod(x : Int, n : Int) = if (x >= 0) x%n else n-((-x)%n)

    for(l in inp) {
        for(c in 0 until l.size) {
           when (l[c]) {
               '>' -> right.add(Pair(r,c))
               '<' -> left.add(Pair(r,c))
               'v' -> down.add(Pair(r,c))
               '^' -> up.add(Pair(r,c))
           }
        }
        r++
    }
    val w = inp[0].size-2
    val h = inp.size-2
    fun pr() {
        for (r in 0..h+1) {
            for (c in 0..w+1) {
                if (r == 0 || r == h+1  || c == 0 || c == w+1)
                    print('#')
                else {
                    val x = Pair(r,c)
                    var n = 0
                    if (x in right) n++
                    if (x in left) n++
                    if (x in up) n++
                    if (x in down) n++
                    if (n > 1) print(n)
                    else {
                        if (x in right) print('>')
                        else if (x in left) print('<')
                        else if (x in up) print('^')
                        else if (x in down) print('v')
                        else print('.')
                    }
                }
            }
            println()
        }
    }
    val visited = mutableListOf(Con(left.toSet(),right.toSet(),up.toSet(),down.toSet()))
    println("> ${right.size}, < ${left.size}, ^ ${up.size}, v ${down.size}")
    for(step in 0.. 10000) {
        right = right.map { (r, c) -> Pair(r, mod((c + 1) - 1, w) + 1) }.toMutableList()
        left = left.map { (r, c) -> Pair(r, mod((c - 1) - 1, w) + 1) }.toMutableList()
        up = up.map { (r, c) -> Pair(mod((r - 1) - 1, h) + 1, c) }.toMutableList()
        down = down.map { (r, c) -> Pair(mod((r + 1) - 1, h) + 1, c) }.toMutableList()
        println("> ${right}, < ${left}, ^ ${up}, v ${down}")
        println("----------------${step+1}")
        pr()
        val ncon = Con(left.toSet(),right.toSet(),up.toSet(),down.toSet())
        if (ncon in visited) {
            break
        } else
            visited.add(ncon)

    }

}
//271
//813