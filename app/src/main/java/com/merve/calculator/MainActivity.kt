package com.merve.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.merve.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var lastNumeric = false
    private var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViews()
    }

    private fun setViews() {
        binding.buttonDot.setOnClickListener {
            if(lastNumeric && !lastDot) {
                binding.tvInput.append(".")
                lastDot = true
                lastNumeric = false
            }
        }

        binding.buttonClr.setOnClickListener {
            binding.tvInput.text = ""
            lastNumeric = false
            lastDot = false
        }

        binding.buttonDivide.setOnClickListener {
            binding.tvInput.text?.let {
                if (lastNumeric) {
                    binding.tvInput.append("/")
                    lastNumeric = false // Update the flag
                    lastDot = false    // Reset the DOT flag
                }
            }
        }

        binding.buttonMultiple.setOnClickListener {
            binding.tvInput.text?.let {
                if (lastNumeric) {
                    binding.tvInput.append("*")
                    lastNumeric = false // Update the flag
                    lastDot = false    // Reset the DOT flag
                }
            }
        }

        binding.buttonMin.setOnClickListener {
            binding.tvInput.text?.let {
                if (lastNumeric) {
                    binding.tvInput.append("-")
                    lastNumeric = false // Update the flag
                    lastDot = false    // Reset the DOT flag
                }
            }
        }

        binding.buttonAdd.setOnClickListener {
            binding.tvInput.text?.let {
                if (lastNumeric) {
                    binding.tvInput.append("+")
                    lastNumeric = false // Update the flag
                    lastDot = false    // Reset the DOT flag
                }
            }
        }

        binding.buttonEqual.setOnClickListener {
            if(lastNumeric) {
                var tvValue = binding.tvInput.text.toString()
                var prefix = ""

                try {
                    if(tvValue.startsWith("-")) {
                        prefix = "-"
                        tvValue = tvValue.substring(1)
                    }
                    when {
                        tvValue.contains("/") -> {
                            val splitedValue = tvValue.split("/")

                            var one = splitedValue[0]
                            val two = splitedValue[1]

                            if(prefix.isNotEmpty()) {
                                one = prefix + one
                            }

                            binding.tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                        }
                        tvValue.contains("*") -> {
                            val splitedValue = tvValue.split("*")

                            var one = splitedValue[0]
                            val two = splitedValue[1]

                            if(prefix.isNotEmpty()) {
                                one = prefix + one
                            }

                            binding.tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                        }
                        tvValue.contains("-") -> {
                            val splitedValue = tvValue.split("-")

                            var one = splitedValue[0]
                            val two = splitedValue[1]

                            if(prefix.isNotEmpty()) {
                                one = prefix + one
                            }

                            binding.tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                        }
                        tvValue.contains("+") -> {
                            val splitedValue = tvValue.split("+")

                            var one = splitedValue[0]
                            val two = splitedValue[1]

                            if(prefix.isNotEmpty()) {
                                one = prefix + one
                            }

                            binding.tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                        }
                    }
                }catch(e:ArithmeticException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun onDigit(view: View) {
        binding.tvInput.append((view as Button).text)
        lastNumeric = true
    }
}

private fun removeZeroAfterDot(result: String): String {

    var value = result

    if (result.contains(".0")) {
        value = result.substring(0, result.length - 2)
    }
    return value
}