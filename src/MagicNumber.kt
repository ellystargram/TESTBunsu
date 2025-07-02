import java.math.BigDecimal
import java.math.BigInteger
import kotlin.math.abs
import kotlin.math.min

class MagicNumber {
    var children: Long = 1
        set(value) {
            val gcd = getGCD(value, parent)
            if (gcd > 1) {
                field = value / gcd
                parent /= gcd
            } else {
                field = value
            }
        }
    var parent: Long = 1
        set(value) {
            if (value == 0L) {
                throw ArithmeticException("Parent must not be 0")
            }
            if (value < 0) {
                children *= -1
            }
            val gcd = getGCD(children, value)
            if (gcd > 1) {
                field = abs(value) / gcd
                children /= gcd
            } else {
                field = value
            }
        }

    constructor()

    constructor(initValue: Long) {
        children = initValue
    }

    constructor(initValue: Int) {
        children = initValue.toLong()
    }

    constructor(initValue: Short) {
        children = initValue.toLong()
    }

    constructor(initValue: Byte) {
        children = initValue.toLong()
    }

    constructor(initValue: BigInteger) {
        children = initValue.toLong()
    }

    constructor(child: Long, parent: Long) {
        children = child
        this.parent = parent
    }

    constructor(child: Int, parent: Int) {
        children = child.toLong()
        this.parent = parent.toLong()
    }

    constructor(child: Short, parent: Short) {
        children = child.toLong()
        this.parent = parent.toLong()
    }

    constructor(value: Double) {
        var value = value
        var powerCount = 0L
        do {
            if (powerCount > 1000) {
                TODO("무한소수 대응 실패")
            }
            value *= 10
            powerCount++
            val longValue = value.toLong()
        } while (longValue.toDouble() != value)

        children = value.toLong()
        parent = pow(10, powerCount)
    }

    constructor(value: Float) {
        var value = value
        var powerCount = 0L
        do {
            if (powerCount > 1000) {
                TODO("무한소수 대응 실패")
            }
            value *= 10
            powerCount++
            val longValue = value.toLong()
        } while (longValue.toFloat() != value)

        children = value.toLong()
        parent = pow(10, powerCount)
    }

    constructor(child: Double, parent: Double) {
        var varChild = child
        var varParent = parent
        var powerCount = 0L
        do {
            if (powerCount > 1000) {
                TODO("무한소수 대응 실패")
            }
            varChild *= 10
            varParent *= 10
            powerCount++
            val longChild = varChild.toLong()
            val longParent = varParent.toLong()
        } while (longChild.toDouble() != varChild || longParent.toDouble() != varParent)
        this.children = varChild.toLong()
        this.parent = varParent.toLong()
    }

    constructor(child: Float, parent: Float) {
        var varChild = child
        var varParent = parent
        var powerCount = 0L
        do {
            if (powerCount > 1000) {
                TODO("무한소수 대응 실패")
            }
            varChild *= 10
            varParent *= 10
            powerCount++
            val longChild = varChild.toLong()
            val longParent = varParent.toLong()
        } while (longChild.toFloat() != varChild || longParent.toFloat() != varParent)
        this.children = varChild.toLong()
        this.parent = varParent.toLong()
    }

    fun getAsDouble(): Double {
        return children.toDouble() / parent.toDouble()
    }

    fun getAsFloat(): Float {
        return children.toFloat() / parent.toFloat()
    }

    fun getAsLong(): Long {
        return children / parent
    }

    fun getAsInt(): Int {
        return children.toInt() / parent.toInt()
    }

    private fun getGCD(a: Long, b: Long): Long {
        val startNumber = min(abs(a), abs(b))
        for (i in startNumber downTo 1) {
            if (a % i == 0L && b % i == 0L) {
                return i
            }
        }
        return 1L
    }

    private fun getLCM(a: Long, b: Long): Long {
        return (a * b) / getGCD(a, b)
    }

    private fun pow(under: Long, up: Long): Long {
        if (up == 0L) {
            return 1
        }
        return pow(under, up - 1) * under
    }

    operator fun plus(other: MagicNumber): MagicNumber {
        val lcmOfParent = getLCM(this.parent, other.parent)
        val thisChildScaled = this.children * (lcmOfParent / this.parent)
        val otherChildScaled = other.children * (lcmOfParent / other.parent)
        return MagicNumber(thisChildScaled + otherChildScaled, lcmOfParent)
    }

    operator fun plus(other: Long): MagicNumber {
        val addChildren = this.parent * other
        return MagicNumber(this.children + addChildren, this.parent)
    }

    operator fun plus(other: BigInteger): MagicNumber {
        return this + MagicNumber(other.toLong())
    }

    operator fun plus(other: Double): MagicNumber {
        return this + MagicNumber(other)
    }

    operator fun plus(other: BigDecimal): MagicNumber {
        return this + MagicNumber(other.toDouble())
    }

    operator fun minus(other: MagicNumber): MagicNumber {
        val lcmOfParent = getLCM(this.parent, other.parent)
        val thisChildScaled = this.children * (lcmOfParent / this.parent)
        val otherChildScaled = other.children * (lcmOfParent / other.parent)
        return MagicNumber(thisChildScaled + otherChildScaled, lcmOfParent)
    }

    operator fun minus(other: Long): MagicNumber {
        val subChildren = this.parent * other
        return MagicNumber(this.children - subChildren, this.parent)
    }

    operator fun minus(other: BigInteger): MagicNumber {
        return this - MagicNumber(other.toLong())
    }

    operator fun minus(other: Double): MagicNumber {
        return this - MagicNumber(other)
    }

    operator fun minus(other: BigDecimal): MagicNumber {
        return this - MagicNumber(other.toDouble())
    }

    operator fun times(other: MagicNumber): MagicNumber {
        if (other.parent == 0L) {
            throw ArithmeticException("Parent must not be 0")
        }
        return MagicNumber(this.children * other.children, this.parent * other.parent)
    }

    operator fun times(other: Long): MagicNumber {
        return MagicNumber(this.children * other, this.parent)
    }

    operator fun times(other: BigInteger): MagicNumber {
        return this * MagicNumber(other.toLong())
    }

    operator fun times(other: Double): MagicNumber {
        val otherMagicNumber = MagicNumber(other)
        return this * otherMagicNumber
    }

    operator fun times(other: BigDecimal): MagicNumber {
        val otherMagicNumber = MagicNumber(other.toDouble())
        return this * otherMagicNumber
    }

    operator fun div(other: MagicNumber): MagicNumber {
        if (other.children == 0L) {
            throw ArithmeticException("Division by zero is not allowed")
        }
        return MagicNumber(this.children * other.parent, this.parent * other.children)
    }

    operator fun div(other: Long): MagicNumber {
        if (other == 0L) {
            throw ArithmeticException("Division by zero is not allowed")
        }
        return MagicNumber(this.children, this.parent * other)
    }

    operator fun div(other: BigInteger): MagicNumber {
        if (other.equals(0)){
            throw ArithmeticException("Division by zero is not allowed")
        }
        return this / other.toLong()
    }

    operator fun div(other: Double): MagicNumber {
        if (other == 0.0) {
            throw ArithmeticException("Division by zero is not allowed")
        }
        return this / MagicNumber(other)
    }

    operator fun div(other: BigDecimal): MagicNumber {
        if (other.equals(0.0)) {
            throw ArithmeticException("Division by zero is not allowed")
        }
        return this / MagicNumber(other.toDouble())
    }
}