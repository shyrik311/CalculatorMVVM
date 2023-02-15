package com.example.calculatormvvm.ui

interface CalculatorView {

    fun showResult(result: String)
    fun showError()
    fun workSpace(value: String)
}