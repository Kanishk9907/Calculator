package com.example.calculator3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {

    private lateinit var etResult: TextView
    private var operand1: Double = 0.0
    private var operand2: Double = 0.0
    private var operator: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etResult = findViewById(R.id.textView)

        val buttons = arrayOf(
            findViewById<Button>(R.id.button_clear),
            findViewById<Button>(R.id.button_modulo),
            findViewById<Button>(R.id.button_divide),
            findViewById<Button>(R.id.button_multiply),
            findViewById<Button>(R.id.button_7),
            findViewById<Button>(R.id.button_8),
            findViewById<Button>(R.id.button_9),
            findViewById<Button>(R.id.button_subtract),
            findViewById<Button>(R.id.button_4),
            findViewById<Button>(R.id.button_5),
            findViewById<Button>(R.id.button_6),
            findViewById<Button>(R.id.button_add),
            findViewById<Button>(R.id.button_1),
            findViewById<Button>(R.id.button_2),
            findViewById<Button>(R.id.button_3),
            findViewById<Button>(R.id.button_equals),
            findViewById<Button>(R.id.button_0),
            findViewById<Button>(R.id.button_decimal)
        )

        for (button in buttons) {
            button.setOnClickListener { v -> onButtonClick(v) }
        }
    }


    private fun onButtonClick(view: View) {
        val button = view as Button
        val buttonText = button.text.toString()

        when {
            buttonText.isDigitsOnly() -> {
                etResult.append(buttonText)
            }
            buttonText == "." -> {
                if (!etResult.text.contains(".")) {
                    etResult.append(buttonText)
                }
            }
            buttonText == "+" || buttonText == "-" || buttonText == "*" || buttonText == "/" -> {
                operator = buttonText
                operand1 = etResult.text.toString().toDouble()
                etResult.text = ""
            }
            buttonText == "=" -> {
                operand2 = etResult.text.toString().toDouble()
                val result = performOperation(operand1, operand2, operator)
                etResult.setText(result.toString())
            }
            buttonText == "C" -> {
                etResult.text=""
            }
        }
    }

    private fun performOperation(operand1: Double, operand2: Double, operator: String): Double {
        return when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> operand1 / operand2
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }
}