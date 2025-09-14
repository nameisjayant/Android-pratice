package com.nameisjayant.androidpractise.ui.performance

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.androidpractise.ui.theme.fontMedium


@Composable
fun TaggingInputUI(
    modifier: Modifier = Modifier
) {
    val tags = remember {
        mutableStateListOf<String>()
    }
    var tag by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .padding(16.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(4.dp)
            )
            .background(
                color = Color(0XFFF2F2F2),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        FlowRow(
            verticalArrangement = Arrangement.spacedBy(
                16.dp,
                Alignment.CenterVertically
            ),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            tags.forEach { tag ->
                TagRow(
                    text = tag,
                    onRemove = {
                        tags.remove(it)
                    }
                )
            }
            TagInputField(
                modifier = Modifier.align(Alignment.CenterVertically),
                tag = tag,
                onValueChange = { value ->
                    tag = value
                },
                onDone = { value ->
                    tags.add(value)
                    tag = ""
                }
            )
        }
    }
}

@Composable
fun TagInputField(
    modifier: Modifier = Modifier,
    tag: String,
    onValueChange: (String) -> Unit,
    onDone: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = tag,
        onValueChange = {
            onValueChange(it)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone(tag)
            }
        ),
        textStyle = TextStyle(
            fontFamily = fontMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = Color.DarkGray
        )
    ) {
        Box {
            if (tag.isEmpty())
                Text(
                    text = "Add topic", style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = fontMedium,
                    )
                )
            it.invoke()
        }
    }
}


@Composable
fun TagRow(
    modifier: Modifier = Modifier,
    text: String,
    onRemove: (String) -> Unit
) {
    Row(
        modifier = modifier
            .border(
                width = 0.5.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .background(
                color = Color.White,
                shape = CircleShape
            )
            .padding(
                horizontal = 12.dp,
                vertical = 6.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = Color.DarkGray,
                fontFamily = fontMedium,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        )
        IconButton(
            onClick = {
                onRemove(text)
            },
            modifier = Modifier.size(18.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.DarkGray
            )
        }
    }
}