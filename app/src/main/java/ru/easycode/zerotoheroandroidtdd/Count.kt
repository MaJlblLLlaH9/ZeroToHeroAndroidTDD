package ru.easycode.zerotoheroandroidtdd

import kotlin.IllegalStateException

interface Count {
    fun initial(number: String): UiState
    fun increment(number: String): UiState
    fun decrement(number: String): UiState
    class Base(private val step: Int, private val max: Int, private val min: Int) : Count {
        override fun initial(number: String): UiState {
            return when (number.toInt()) {
                min -> UiState.Min(number)
                max -> UiState.Max(number)
                else -> UiState.Base(number)
            }
        }

        init {
            if (step <= 0)
                throw IllegalStateException("step should be positive, but was $step")
            if (max <= 0)
                throw IllegalStateException("max should be positive, but was $max")
            if (max < step)
                throw IllegalStateException("max should be more than step")
            if (max <= min)
                throw java.lang.IllegalStateException("max should be more than min")
        }

        override fun increment(number: String): UiState {
            val digits = number.toInt()
            val result = digits + step
            return initial(result.toString())
        }

        override fun decrement(number: String): UiState {
            val digits = number.toInt()
            val result = digits - step
            return initial(result.toString())
        }

    }
}