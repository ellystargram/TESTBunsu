fun main() {
    val number1 = MagicNumber(1, 3)
    val number2 = MagicNumber(3, 1)
    val result = number1 / number2

    println("Result: ${result.children}/${result.parent} = ${result.getAsDouble()}")

    val a:Double = 0.1
    val b:Double = 0.2
    val c:Double = 0.3
    println("a + b = ${a + b} == c: ${a + b == c}")
//    val d = MagicNumber(a+b) // infinity sosu is causing error
//    println("D: ${d.children}/${d.parent} = ${d.getAsDouble()}")

    val a1 = MagicNumber(1, 10)
    val b1 = MagicNumber(2, 10)
    val c1 = MagicNumber(3,10)
    val c2 = a1+b1
    println("a1 + b1 = c1: ${(c1).getAsDouble()} == c2: ${c2.getAsDouble() == 0.3}")

//    val error = MagicNumber().apply {
//        children = 1
//        parent = 0
//    }

    var test = MagicNumber(0.5)

    println("test child= ${test.children}, test parent= ${test.parent}}" )
    test += 0.5
    println("test ADD = ${test.getAsDouble()}")
}
