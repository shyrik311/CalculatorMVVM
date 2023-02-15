package com.example.calculatormvvm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.calculatormvvm.Calculator
import com.example.calculatormvvm.WorkSpace
import com.example.calculatormvvm.ui.CalculatorView

class CalculatorViewModel(private var calculator: Calculator, private var view: CalculatorView) {
    private var workSpace = WorkSpace()
    private var clearAll = Calculator()
    private val _expression = MutableLiveData<String>()
    val expression: LiveData<String> = _expression

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    fun number(value: String) {
        _expression.value = workSpace.addToWorkSpace(value)
    }

    fun operator(operator: String) {
        val replacementOperator = when (operator) {
            "/" -> "÷"
            "*" -> "×"
            else -> operator
        }
        _expression.value = workSpace.addToWorkSpace(replacementOperator)
    }

    fun parentheses(isLeft: Boolean) {
        val bracket = if (isLeft) "(" else ")"
        _expression.value = workSpace.addToWorkSpace(bracket)
    }

    fun equals() {
        val expression = workSpace.getExpression().replace("÷", "/").replace("×", "*")
        try {
            val result = calculator.calculate(expression)
            _result.value = result
            _error.value = false
        } catch (e: Exception) {
            _error.value = true
        }
    }

    fun clearAll() {
        clearAll = Calculator()
        workSpace = WorkSpace()
        view.workSpace("")
        view.showResult("")
    }


    fun backspace() {
        val currentExpression = workSpace.getExpression()
        if (currentExpression.isNotEmpty()) {
            val newExpression = currentExpression.substring(0, currentExpression.length - 1)
            workSpace.setExpression(newExpression)
            _expression.value = newExpression
        }
    }
}