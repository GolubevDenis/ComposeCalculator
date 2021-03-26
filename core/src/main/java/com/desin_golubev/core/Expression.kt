package com.desin_golubev.core

import androidx.annotation.VisibleForTesting

interface Expression {
    fun calculate(): Double
}

data class NumExp(
    private val value: Double
): Expression {
    override fun calculate(): Double = value
}

internal fun Double.toExp(): Expression = NumExp(this)

open class DoubleExp(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE) val a: Expression,
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE) val b: Expression,
    private val operation: Double.(Double) -> Double
) : Expression {
    override fun calculate(): Double = a.calculate().operation(b.calculate())
}

class AddExp(
    a: Expression,
    b: Expression
) : DoubleExp(a, b, Double::plus)

class SubtractExp(
    a: Expression,
    b: Expression
) : DoubleExp(a, b, Double::minus)

class MultiplyExp(
    a: Expression,
    b: Expression
) : DoubleExp(a, b, Double::times)

class DivideExp(
    a: Expression,
    b: Expression
) : DoubleExp(a, b, Double::div)