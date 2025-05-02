package com.nameisjayant.androidpractise.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MapWithComposeScreen(modifier: Modifier = Modifier) {

    val prices = remember {
        listOf(100, 200, 300, 100, 40, 20, 100, 20, 20, 40)
    }

    val discountedPrice by remember {
        derivedStateOf {
            prices.map { it * 0.9 }
        }
    }
    val discount by produceState(initialValue = emptyList<Double>(), prices) {
        value = prices.map { it * 0.9 }
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            items = discountedPrice
        ) {
            Text(
                "Discount -> $it", style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500
                )
            )
        }
    }

}

@Composable
fun GroupByWithComposeScreen(
    modifier: Modifier = Modifier
) {

    val list = remember {
        listOf(
            "Anshul",
            "Aman",
            "Bobby",
            "Amit",
            "Chahal",
            "Binny",
            "Danny",
            "Bady",
            "Anna",
            "Chuna"
        )
    }

    val groupByFirstLetter by remember {
        derivedStateOf {
            list.groupBy { it.first() }
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        groupByFirstLetter.forEach { (type, names) ->
            item {
                Text("$type", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
            }
            items(
                names
            ) {
                Text(it)
            }
        }
    }
}