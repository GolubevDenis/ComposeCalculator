package com.desin_golubev.core

import junit.framework.TestCase

class CalculatorImplTest : TestCase() {

    fun testCalculate() {
        val calculator = CalculatorImpl()
        val actual = calculator.calculate("2+2")
        assertEquals(4, actual)
    }

}