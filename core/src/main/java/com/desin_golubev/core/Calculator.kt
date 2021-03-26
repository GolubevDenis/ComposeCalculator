package com.desin_golubev.core

interface Calculator {
    fun calculate(expression: String): Double
}

class CalculatorImpl : Calculator {
    override fun calculate(expression: String): Double {
        return 0.0
    }
}