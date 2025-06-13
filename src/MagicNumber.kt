import kotlin.math.abs
import kotlin.math.min

class MagicNumber {
    var children: Long = 1
        set(value) {
            val gcd = getGCDOfBunsu(value, parent)
            if (gcd > 1) {
                field = value / gcd
                parent /= gcd
            } else {
                field = value
            }
        }
    var parent: Long = 1
        set(value) {
            if (value < 0) {
                children *= -1
            }
            val gcd = getGCDOfBunsu(children, value)
            if (gcd > 1) {
                field = abs(value) / gcd
                children /= gcd
            } else {
                field = value
            }
        }

    fun getAsDouble(): Double {
        return children.toDouble() / parent.toDouble()
    }

    private fun getGCDOfBunsu(a: Long, b: Long): Long {
        val startNumber = min(abs(a), abs(b))
        for (i in startNumber downTo 1) {
            if (a % i == 0L && b % i == 0L) {
                return i
            }
        }
        return 1L
    }

    private fun getLCMOfBunsu(a: Long, b: Long): Long {
        return (a * b) / getGCDOfBunsu(a, b)
    }

    operator fun plus(other: MagicNumber): MagicNumber {
        if (this.parent == other.parent) {
            val result = MagicNumber()
            result.parent = this.parent
            result.children = this.children + other.children
            return result
        }
        val lcm = getLCMOfBunsu(this.parent, other.parent)
        val thisChildScaled = this.children * (lcm / this.parent)
        val otherChildScaled = other.children * (lcm / other.parent)
        val result = MagicNumber()
        result.parent = lcm
        result.children = thisChildScaled + otherChildScaled
        return result
    }

    operator fun plus(other: Long): MagicNumber {
        val addChildren = this.parent * other
        val result = MagicNumber()
        result.parent = this.parent
        result.children = this.children + addChildren
        return result
    }

    operator fun minus(other: MagicNumber): MagicNumber {
        if (this.parent == other.parent) {
            val result = MagicNumber()
            result.parent = this.parent
            result.children = this.children - other.children
            return result
        }
        val lcm = getLCMOfBunsu(this.parent, other.parent)
        val thisScaled = this.children * (lcm / this.parent)
        val otherScaled = other.children * (lcm / other.parent)
        val result = MagicNumber()
        result.parent = lcm
        result.children = thisScaled - otherScaled
        return result
    }

    operator fun minus(other: Long): MagicNumber {
        val subChildren = this.parent * other
        val result = MagicNumber()
        result.parent = this.parent
        result.children = this.children - subChildren
        return result
    }

    operator fun times(other: MagicNumber): MagicNumber {
        val result = MagicNumber()
        result.parent = this.parent * other.parent
        result.children = this.children * other.children
        return result
    }

    operator fun times(other: Long): MagicNumber {
        val result = MagicNumber()
        result.parent = this.parent * other
        result.children = this.children
        return result
    }

    operator fun div(other: MagicNumber): MagicNumber {
        if (other.children == 0L) {
            throw ArithmeticException("Division by zero is not allowed")
        }
        val inverse = MagicNumber().apply {
            parent = other.children
            children = other.parent
        }
        return this * inverse
    }

    operator fun div(other: Long): MagicNumber {
        val result = MagicNumber()
        result.parent = this.parent * other
        result.children = this.children
        return result
    }
}