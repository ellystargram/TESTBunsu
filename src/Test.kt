fun main() {
    var number1 = MagicNumber().apply {
        children = 1
        parent = 3
    }
    var number2 = MagicNumber().apply {
        children = 3
        parent = 1
    }
    var result = number1 / number2

    println("Result: ${result.children}/${result.parent} = ${result.getAsDouble()}")

    var a:Double = 0.1
    var b:Double = 0.2
    var c:Double = 0.3
    println("a + b = ${a + b} == c: ${a + b == c}")

    var a1 = MagicNumber().apply {
        children = 1
        parent = 10
    }
    var b1 = MagicNumber().apply {
        children = 2
        parent = 10
    }
    var c1 = MagicNumber().apply {
        children = 3
        parent = 10
    }
    println("a1 + b1 = ${(a1 + b1).getAsDouble()} == c1: ${(a1 + b1).getAsDouble() == 0.3}")
}