package com.example.calculator.ui.theme

import androidx.lifecycle.ViewModel
import com.example.calculator.CalculatorUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalculatorViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()

    fun updateResult(result: Int) {
        _uiState.update { currentState ->
            currentState.copy(resultNumber = result)
        }
    }

    fun resetResult() {
        _uiState.update { currentState ->
            currentState.copy(resultNumber = 0)
        }
    }

}