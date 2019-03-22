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

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matchers.closeTo
import org.junit.Assert.assertThat

/**
 * JUnit4 unit tests for the calculator logic. These are local unit tests; no device needed
 */
@RunWith(JUnit4::class)
class CalculatorTest {

    /**
     * Set up the environment for testing
     */
    @Before
    fun setUp() {

    }

    /**
     * Test for simple addition
     */
    @Test
    fun addTwoNumbers() {
        val resultAdd = Calculator.add(1.0, 1.0)
        assertThat(resultAdd, `is`(equalTo(2.0)))
    }

    @Test
    fun addTwoNumbersNegative(){
        val resultAdd = Calculator.add(-1.0, 2.0)
        assertThat(resultAdd, `is`(equalTo(1.0)))
    }

    @Test
    fun addTwoNumbersFloats() {
        val resultAdd = Calculator.add(1.111, 1.111)
        assertThat(resultAdd, `is`(closeTo(2.222, 0.01)))
    }

}