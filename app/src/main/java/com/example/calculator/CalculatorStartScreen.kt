package com.example.calculator

//import android.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme

@Composable
fun CalculatorStartScreen(
    onNextButtonClicked: () -> Unit,
    onResultUpdate: (Int) -> Unit
) {
    var firstValue: Int? by remember { mutableStateOf(null) }
    var secondValue: Int? by remember { mutableStateOf(null) }

    var inputNumber by remember { mutableStateOf("") }
    var choosedAction: String? by remember { mutableStateOf(null) }

    var result by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = inputNumber,
            onValueChange = { inputNumber = it},
            label = { Text(text = "Введіть число") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() })
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ActionButton(
                text = "+",
                onClick = {
                    if(inputNumber != "") {
                        firstValue = inputNumber.toInt()
                        inputNumber = ""
                        choosedAction = "add"
                    }
                }
            )
            ActionButton(
                text = "-",
                onClick = {
                    if(inputNumber != "") {
                        firstValue = inputNumber.toInt()
                        inputNumber = ""
                        choosedAction = "subtract"
                    }
                }
            )
            ActionButton(
                text = "×",
                onClick = {
                    if(inputNumber != "") {
                        firstValue = inputNumber.toInt()
                        inputNumber = ""
                        choosedAction = "multiply"
                    }
                }
            )
            ActionButton(
                text = "÷",
                onClick = {
                    firstValue = inputNumber.toInt()
                    inputNumber = ""
                    choosedAction = "divide"
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ActionButton(
                modifier = Modifier.width(130.dp),
                text = "=",
                onClick = {
                    secondValue = inputNumber.toInt()
                    inputNumber = ""
                    result = calculate(firstValue!!, secondValue!!, choosedAction!!).toString()
                    firstValue = null
                    secondValue = null
                    choosedAction = null
                    onResultUpdate(result.toInt())
                }
            )
            ActionButton(
                modifier = Modifier.width(130.dp),
                text = "AC",
                onClick = {
                    firstValue = null
                    secondValue = null
                    inputNumber = ""
                    result = ""
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        ResultField(result)
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58)),
            onClick = onNextButtonClicked ) {
            Text(
                text = "Перейти до результату",
                color = Color.White
            )
        }
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58)),
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            color = Color.White
        )
    }
}

private fun calculate(
    firstNumber: Int,
    secondNumber: Int,
    action: String
): Int {
    var result = 0
    result = when(action) {
        "add" -> firstNumber + secondNumber
        "subtract" -> firstNumber - secondNumber
        "multiply" -> firstNumber * secondNumber
        "divide" -> firstNumber / secondNumber
        else -> 0
    }
    return result
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculatorTheme {
        CalculatorApp()
    }
}