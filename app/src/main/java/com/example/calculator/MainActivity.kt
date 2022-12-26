package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.CalculatorViewModel

enum class CalculatorScreen() {
    Start,
    Result
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CalculatorApp()
                }
            }
        }
    }
}

@Composable
fun CalculatorApp(
    calculatorViewModel: CalculatorViewModel = viewModel()
) {
    val calculatorUiState by calculatorViewModel.uiState.collectAsState()

    val navController = rememberNavController()
    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        NavHost(
            navController = navController,
            startDestination = CalculatorScreen.Start.name
        ) {
            composable(route = CalculatorScreen.Start.name) {
                CalculatorStartScreen(
                    onNextButtonClicked = {
                        navController.navigate(CalculatorScreen.Result.name)
                    },
                    onResultUpdate = { calculatorViewModel.updateResult(it) }
                )
            }
            composable(route = CalculatorScreen.Result.name) {
                ResultScreen(
                    result = calculatorUiState.resultNumber.toString(),
                    onBackButtonClicked = { resetResultAndNavigateToStart(
                        calculatorViewModel,
                        navController
                    ) }
                )
            }
        }
    }
}

private fun resetResultAndNavigateToStart(
    viewModel: CalculatorViewModel,
    navController: NavHostController
) {
    viewModel.resetResult()
    navController.popBackStack(CalculatorScreen.Start.name, inclusive = false)
}