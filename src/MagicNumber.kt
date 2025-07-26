import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext

class MagicNumber {
    var children: BigInteger = BigInteger.ONE
        set(value) {
            val gcd = value.gcd(parent)
            if (gcd > BigInteger.ONE) {
                field = value / gcd
                parent /= gcd
            } else {
                field = value
            }
        }
    var parent: BigInteger = BigInteger.ONE
        set(value) {
            if (value == BigInteger.ZERO) {
                throw ArithmeticException("Parent must not be 0")
            }
            if (value < BigInteger.ZERO) {
                children *= (-1).toBigInteger()
            }
            val gcd = value.gcd(children)
            if (gcd > BigInteger.ONE) {
                field = value.abs() / gcd
                children /= gcd
            } else {
                field = value
            }
        }

    constructor()

    constructor(initValue: Long) {
        children = initValue.toBigInteger()
    }

    constructor(initValue: Int) {
        children = initValue.toBigInteger()
    }

    constructor(initValue: Short) {
        children = initValue.toLong().toBigInteger()
    }

    constructor(initValue: Byte) {
        children = initValue.toLong().toBigInteger()
    }

    constructor(initValue: BigInteger) {
        children = initValue
    }

    constructor(child: BigInteger, parent: BigInteger) {
        children = child
        this.parent = parent
    }

    constructor(child: Long, parent: Long) {
        children = child.toBigInteger()
        this.parent = parent.toBigInteger()
    }

    constructor(child: Int, parent: Int) {
        children = child.toBigInteger()
        this.parent = parent.toBigInteger()
    }

    constructor(child: Short, parent: Short) {
        children = child.toLong().toBigInteger()
        this.parent = parent.toLong().toBigInteger()
    }

    constructor(value: Double) {
        var value = value
        var powerCount = 0
        do {
            if (powerCount > 1000) {
                TODO("무한소수 대응 실패")
            }
            value *= 10
            powerCount++
            val longValue = value.toLong()
        } while (longValue.toDouble() != value)

        children = value.toBigDecimal().toBigInteger()
        parent = BigInteger.TEN.pow(powerCount)
    }

    constructor(value: Float) {
        var value = value
        var powerCount = 0
        do {
            if (powerCount > 1000) {
                TODO("무한소수 대응 실패")
            }
            value *= 10
            powerCount++
            val longValue = value.toLong()
        } while (longValue.toFloat() != value)

        children = value.toBigDecimal().toBigInteger()
        parent = BigInteger.TEN.pow(powerCount)
    }

    constructor(value: BigDecimal) {
        var value = value
        var powerCount = 0
        do {
            if (powerCount > 1000) {
                TODO("무한소수 대응 실패")
            }
            value *= BigDecimal.TEN
            powerCount++
            val bigIntegerValue = value.toBigInteger()
        } while (bigIntegerValue.toBigDecimal().compareTo(value) != 0)

        children = value.toBigInteger()
        parent = BigInteger.TEN.pow(powerCount)
    }

    constructor(child: Double, parent: Double) {
        var varChild = child
        var varParent = parent
        var powerCount = 0
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
        this.children = varChild.toLong().toBigInteger()
        this.parent = varParent.toLong().toBigInteger()
    }

    constructor(child: Float, parent: Float) {
        var varChild = child
        var varParent = parent
        var powerCount = 0
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
        this.children = varChild.toLong().toBigInteger()
        this.parent = varParent.toLong().toBigInteger()
    }

    fun getAsDouble(): Double {
        return getAsBigDecimal().toDouble()
    }

    fun getAsFloat(): Float {
        return getAsBigDecimal().toFloat()
    }

    fun getAsBigDecimal(): BigDecimal {
        return children.toBigDecimal().divide(parent.toBigDecimal(), MathContext.DECIMAL128)
    }

    fun getAsLong(): Long {
        return getAsBigDecimal().toLong()
    }

    fun getAsInt(): Int {
        return getAsBigDecimal().toInt()
    }

    fun getAsBigInteger(): BigInteger {
        return getAsBigDecimal().toBigInteger()
    }

    private fun getLCM(a: BigInteger, b: BigInteger): BigInteger {
        return (a * b) / a.gcd(b)
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
        val addChildren = this.parent * other.toBigInteger()
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
        val subChildren = this.parent * other.toBigInteger()
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
        if (other.parent == BigInteger.ZERO) {
            throw ArithmeticException("Parent must not be 0")
        }
        return MagicNumber(this.children * other.children, this.parent * other.parent)
    }

    operator fun times(other: Long): MagicNumber {
        return MagicNumber(this.children * other.toBigInteger(), this.parent)
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
        if (other.children == BigInteger.ZERO) {
            throw ArithmeticException("Division by zero is not allowed")
        }
        return MagicNumber(this.children * other.parent, this.parent * other.children)
    }

    operator fun div(other: Long): MagicNumber {
        if (other == 0L) {
            throw ArithmeticException("Division by zero is not allowed")
        }
        return MagicNumber(this.children, this.parent * other.toBigInteger())
    }

    operator fun div(other: BigInteger): MagicNumber {
        if (other.equals(0)) {
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