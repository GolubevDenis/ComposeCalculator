package com.desin_golubev.core

import junit.framework.TestCase
import kotlin.reflect.KClass

class ExpressionParserImplTest : TestCase() {

    private val parser = ExpressionParserImpl()

    fun testParseSimpleAdd() {
        val exp = parser.parse("1+2")
        assertExpType(AddExp::class, exp)
        assertSimpleDoubleExp(1.0,2.0, exp)
    }

    fun testParseComplexExpression() {
        val exp = parser.parse("7*9+10-6")

        assertExpType(AddExp::class, exp)
        exp as AddExp
        assertExpType(MultiplyExp::class, exp.a)
        assertExpType(SubtractExp::class, exp.b)
    }

    fun testParseExpressionWithBrackets() {
        val exp = parser.parse("(2+2)*2")

        assertExpType(MultiplyExp::class, exp)
        exp as MultiplyExp
        assertExpType(AddExp::class, exp.a)
        assertExpType(NumExp::class, exp.b)
    }

    fun testParseSimpleSub() {
        val exp = parser.parse("3-1")
        assertExpType(SubtractExp::class, exp)
        assertSimpleDoubleExp(3.0,1.0, exp)
    }

    fun testParseSimpleMul() {
        val exp = parser.parse("3*2")
        assertExpType(MultiplyExp::class, exp)
        assertSimpleDoubleExp(3.0,2.0, exp)
    }

    fun testParseSimpleDiv() {
        val exp = parser.parse("6/3")
        assertExpType(DivideExp::class, exp)
        assertSimpleDoubleExp(6.0,3.0, exp)
    }

    private fun assertExpType(expected: KClass<out Expression>, exp: Expression) {
        if(!expected.isInstance(exp))
            assert(false) { "Expected type is ${expected.simpleName}, but actual is ${exp::class.simpleName}" }
    }

    private fun assertSimpleDoubleExp(expectedNumA: Double, expectedNumB: Double, exp: Expression) {
        assert(exp is DoubleExp)
        exp as DoubleExp
        assertEquals(expectedNumA.toExp(), exp.a)
        assertEquals(expectedNumB.toExp(), exp.b)
    }

}