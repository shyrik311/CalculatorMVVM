package com.example.calculatormvvm

import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class Calculator {
    private val parser = Expression()


    fun calculate(expression: String): String {
        parser.expressionString = expression
        val result = parser.calculate()
        if (result.isNaN()) {
            return "error"
        }
        val formatter = DecimalFormat("0.#####")
        return formatter.format(result)
    }
}