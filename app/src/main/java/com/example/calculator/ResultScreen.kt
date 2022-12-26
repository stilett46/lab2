package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(result: String, onBackButtonClicked: () -> Unit) {
    Column {
        ResultField(result)
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onBackButtonClicked,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58)),
        ) {
            Text(
                text = "Повернутися",
                color = Color.White
            )
        }
    }
}

@Composable
fun ResultField(
    result: String
) {
    Column {
        Text(
            text = "Результат:"
        )
        Row(
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Text(
                text = result,
                color = Color.White
            )
        }
    }
}