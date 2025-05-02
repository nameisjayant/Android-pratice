package com.nameisjayant.androidpractise.ui.compose

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp


inline fun Modifier.ifThen(condition: Boolean, block: Modifier.() -> Modifier): Modifier {
    return if (condition) then(block(Modifier)) else this
}

@Composable
fun ModifierScreen(modifier: Modifier = Modifier) {
    Text("Total: ₹500".toAnnotatedText("₹500"), modifier = modifier)

}

@Composable
fun <T> State<T>.onChange(action: (T) -> Unit): State<T> {
    val currentValue = value
    LaunchedEffect(currentValue) {
        action(currentValue)
    }
    return this

}

fun <T> SnapshotStateList<T>.toggle(item: T) {
    if (contains(item)) remove(item) else add(item)
}

fun LazyListState.isAtBottom(): Boolean {
    return layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
}

@Composable
fun Float.dpToPx(): Float = with(LocalDensity.current) { this@dpToPx.dp.toPx() }

@Composable
fun Int.dpToPx(): Float = with(LocalDensity.current) { this@dpToPx.dp.toPx() }

fun String.toAnnotatedText(
    keyword: String,
    keywordStyle: SpanStyle = SpanStyle(color = Color.Red)
): AnnotatedString {
    val builder = AnnotatedString.Builder()
    val start = indexOf(keyword)
    if (start == -1) return AnnotatedString(this)
    builder.append(substring(0, start))
    builder.pushStyle(keywordStyle)
    builder.append(keyword)
    builder.pop()
    builder.append(substring(start + keyword.length))
    return builder.toAnnotatedString()
}