package com.nameisjayant.androidpractise.ui.performance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.androidpractise.ui.theme.fontMedium


fun cardWithSpaces(digit: String) = digit.chunked(4).joinToString(" ")

class CreditCardVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val numbers = text.text.filter { it.isDigit() }
        val grouped = cardWithSpaces(numbers)

        val maskedFirst12Digit = buildString {
            var count = 0
            for (ch in grouped) {
                if (ch.isDigit()) {
                    count++
                    if (count <= 12) append('*') else append(ch)
                } else append(ch)
            }
        }

        // Offset mapping: map cursor position roughly from original to transformed
        val offsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                var transformedIndex = 0
                var seen = 0
                while (seen < offset && transformedIndex < grouped.length) {
                    if (grouped[transformedIndex].isDigit()) seen++
                    transformedIndex++
                }
                return transformedIndex
            }


            override fun transformedToOriginal(offset: Int): Int {
                var seen = 0
                var i = 0
                while (i < offset && i < grouped.length) {
                    if (grouped[i].isDigit()) seen++
                    i++
                }
                return seen
            }
        }
        return TransformedText(AnnotatedString(maskedFirst12Digit), offsetTranslator)
    }

}

fun onlyDigits(text: String): String = text.filter { it.isDigit() }
fun isValidCardLength(digits: String): Boolean = digits.length == 16

@Composable
fun CreditCardScreen(
    modifier: Modifier = Modifier
) {
    var card by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = card,
            onValueChange = {
                val numbers = onlyDigits(it).take(16)
                card = cardWithSpaces(numbers)
            },
            placeholder = {
                Text(
                    "For eg- 1234 5678 9012 3456", style = TextStyle(
                        color = Color.Gray,
                        fontFamily = fontMedium,
                        fontSize = 14.sp
                    )
                )
            },
            visualTransformation = CreditCardVisualTransformation()
        )
        TextField(
            modifier = Modifier.fillMaxWidth(0.4f),
            value = date,
            onValueChange = { date = it },
            placeholder = {
                Text(
                    "For eg- 12/30", style = TextStyle(
                        color = Color.Gray,
                        fontFamily = fontMedium,
                        fontSize = 14.sp
                    )
                )
            }
        )
    }
}