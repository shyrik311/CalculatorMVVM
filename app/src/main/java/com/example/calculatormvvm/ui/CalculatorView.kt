package com.example.calculatormvvm.ui

interface CalculatorView {

    fun showResult(result: String)
    fun showError()
    fun updateWorkSpace(value: String)
}