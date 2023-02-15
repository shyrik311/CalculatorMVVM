package com.example.calculatormvvm

class WorkSpace {
    private var expression = ""

    fun addToWorkSpace(value: String): String {
        expression += value
        return expression
    }

    fun getExpression(): String {
        return expression
    }

    fun setExpression(newExpression: String) {
        expression = newExpression
    }
}