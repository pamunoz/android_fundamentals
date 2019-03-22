/*
 * Copyright 2018, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.SimpleCalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*


/**
 * SimpleCalc is the initial version of SimpleCalcTest.  It has
 * a number of intentional oversights for the student to debug/fix,
 * including input validation (no input, bad number format, div by zero)
 *
 * In addition there is only one (simple) unit test in this app.
 * All the input validation and the unit tests are added as part of the lessons.
 *
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * OnClick method called when the add Button is pressed.
     */
    fun onAdd(view: View) = compute(Calculator.Operator.ADD)

    /**
     * OnClick method called when the subtract Button is pressed.
     */
    fun onSub(view: View) = compute(Calculator.Operator.SUB)

    /**
     * OnClick method called when the divide Button is pressed.
     */
    fun onDiv(view: View) {
        try {
            compute(Calculator.Operator.DIV)
        } catch (iae: IllegalArgumentException) {
            Log.e(TAG, "IllegalArgumentException", iae)
            operation_result_text_view!!.text = getString(R.string.computationError)
        }

    }

    /**
     * OnClick method called when the multiply Button is pressed.
     */
    fun onMul(view: View) = compute(Calculator.Operator.MUL)

    private fun compute(operator: Calculator.Operator) {
        val operandOne: Double
        val operandTwo: Double
        val result: String
        try {
            when {
                operand_one_edit_text.text.toString().isEmpty() -> {
                    operation_result_text_view!!.text =  getString(R.string.error_empty_field)
                    return
                }
                operand_two_edit_text.text.toString().isEmpty() -> {
                    operation_result_text_view!!.text = getString(R.string.error_empty_field)
                    return
                }
                else -> {
                    operandOne = operand_one_edit_text.text.toString().toDouble()
                    operandTwo = operand_two_edit_text.text.toString().toDouble()
                }
            }

        } catch (nfe: NumberFormatException) {
            Log.e(TAG, "NumberFormatException", nfe)
            return
        }

        result = when (operator) {
            Calculator.Operator.ADD -> Calculator.add(operandOne, operandTwo).toString()
            Calculator.Operator.SUB -> Calculator.sub(operandOne, operandTwo).toString()
            Calculator.Operator.DIV -> {
                when {
                    operandOne == 0.0 -> "Numerator must be other than 0"
                    operandTwo == 0.0 -> "Denominator must be different than 0"
                    else -> Calculator.div(operandOne, operandTwo).toString()
                }
            }
            Calculator.Operator.MUL -> Calculator.mul(operandOne, operandTwo).toString()
        }
        operation_result_text_view!!.text = result
    }

    companion object {

        private const val TAG = "CalculatorActivity"

    }
}