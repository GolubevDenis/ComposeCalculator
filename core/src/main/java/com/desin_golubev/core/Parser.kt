package com.desin_golubev.core

import java.lang.Exception

interface ExpressionParser {
    fun parse(expression: String): Expression
}

class ExpressionParserImpl : ExpressionParser {

    private val expMapper = mapOf(
        "+" to AddExp::class.java,
        "-" to SubtractExp::class.java,
        "*" to MultiplyExp::class.java,
        "/" to DivideExp::class.java
    ).mapValues {
        it.value.constructors.first()
    }

    override fun parse(expression: String): Expression {

        PRIORITY.forEach {
            it.forEach {
                if(expression.contains(it)) {
                    val expressionParts = expression.split(it).map {
                        it.toDoubleOrNull()?.toExp() ?: parse(it)
                    }
                    return expMapper[it]?.newInstance(expressionParts.first(), expressionParts.last()) as Expression
                }
            }
        }
        throw Exception("Cannot parse expression: $expression")
    }
}
