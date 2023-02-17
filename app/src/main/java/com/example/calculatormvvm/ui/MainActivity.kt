package com.example.calculatormvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatormvvm.Calculator
import com.example.calculatormvvm.data.CalculatorViewModel
import com.example.calculatormvvm.R
import com.example.calculatormvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CalculatorView {
    private lateinit var calculatorPresenter: CalculatorViewModel
    private lateinit var binding: ActivityMainBinding
    private var isLeftBracket = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        calculatorPresenter = CalculatorViewModel(Calculator(), this)
        setListeners()
        observeData()
    }

    private fun setListeners() {
        binding.clearAll.setOnClickListener { calculatorPresenter.clearAll() }
        binding.one.setOnClickListener { calculatorPresenter.number("1") }
        binding.two.setOnClickListener { calculatorPresenter.number("2") }
        binding.three.setOnClickListener { calculatorPresenter.number("3") }
        binding.four.setOnClickListener { calculatorPresenter.number("4") }
        binding.five.setOnClickListener { calculatorPresenter.number("5") }
        binding.six.setOnClickListener { calculatorPresenter.number("6") }
        binding.seven.setOnClickListener { calculatorPresenter.number("7") }
        binding.eight.setOnClickListener { calculatorPresenter.number("8") }
        binding.nine.setOnClickListener { calculatorPresenter.number("9") }
        binding.zero.setOnClickListener { calculatorPresenter.number("0") }
        binding.dot.setOnClickListener { calculatorPresenter.number(".") }
        binding.plus.setOnClickListener { calculatorPresenter.operator("+") }
        binding.minus.setOnClickListener { calculatorPresenter.operator("-") }
        binding.div.setOnClickListener { calculatorPresenter.operator("/") }
        binding.multiply.setOnClickListener { calculatorPresenter.operator("*") }
        binding.equals.setOnClickListener { calculatorPresenter.equals() }
        binding.parentheses.setOnClickListener {
            calculatorPresenter.parentheses(isLeftBracket)
            isLeftBracket = !isLeftBracket
        }
        binding.backSpace.setOnClickListener { calculatorPresenter.backspace() }
    }

    private fun observeData() {
        calculatorPresenter.expressionLiveData.observe(this) { value ->
            binding.workSpace.text = value
        }
        calculatorPresenter.result.observe(this) {
            binding.resultShow.text = it
        }
        calculatorPresenter.error.observe(this) {
            if (it) {
                binding.resultShow.text = getString(R.string.error)
            }
        }
    }

    override fun showResult(result: String) {
        binding.resultShow.text = result
    }

    override fun updateWorkSpace(value: String) {
        binding.workSpace.text = value
    }

    override fun showError() {
        binding.resultShow.text = (R.string.error.toString())
    }
}