package com.desin_golubev.core

import junit.framework.TestCase
import org.junit.Test

class DoubleExpTest : TestCase() {

    @Test
    fun testCalculate() {
        val num1 = NumExp(2.0)
        val num2 = NumExp(3.0)
        val expression = DoubleExp(num1, num2, Double::plus)
        assertEquals(5.0, expression.calculate())
    }

}