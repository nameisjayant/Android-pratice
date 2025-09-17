package com.nameisjayant.androidpractise.ui.lazycolumnImplementations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.androidpractise.ui.theme.fontMedium
import kotlinx.coroutines.launch


@Composable
fun ContactWithAlphabetSearchScreen(
    modifier: Modifier = Modifier
) {

    val contactList = remember {
        namesByLetter.flatMap { (letter, names) ->
            names.map {
                it
            }
        }
    }
    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()

    val indexMap = remember {
        contactList.mapIndexed { index, name ->
            val letter = name.first().uppercaseChar()
            letter to index
        }.distinctBy { it.first }.toMap()
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Contact App",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = fontMedium,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        )
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ContactList(
                lazyListState = lazyListState,
                items = contactList
            )
            AlphabetRow(
                modifier = Modifier.align(Alignment.CenterEnd),
                onMove = { char ->
                    indexMap[char]?.let { index ->
                        scope.launch {
                            lazyListState.animateScrollToItem(index)
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun ContactList(
    modifier: Modifier = Modifier,
    items: List<String>,
    lazyListState: LazyListState
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(
            items
        ) { _, item ->
            Text(
                text = item,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = fontMedium,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Composable
fun AlphabetRow(
    modifier: Modifier = Modifier,
    onMove: (letter: Char) -> Unit
) {
    Column(
        modifier = modifier
            .padding(4.dp)
            .background(Color.LightGray, CircleShape)
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        ('A'..'Z').forEach { letter ->
            Text(
                text = "$letter",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = fontMedium,
                    fontWeight = FontWeight.Medium,
                ),
                modifier = Modifier
                    .clickable(
                        onClick = {
                            onMove(letter)
                        }
                    )
            )
        }
    }
}

val namesByLetter = mapOf(
    'A' to listOf("Amit", "Anjali", "Arun", "Akash", "Asha"),
    'B' to listOf("Bharat", "Bhavna", "Bhuvan", "Bobby", "Bindu"),
    'C' to listOf("Chandan", "Chitra", "Chetan", "Charu", "Chirag"),
    'D' to listOf("Deepak", "Divya", "Dinesh", "Dev", "Diksha"),
    'E' to listOf("Ekta", "Esha", "Eklavya", "Elina", "Eshan"),
    'F' to listOf("Farhan", "Fatima", "Faizan", "Firoz", "Falguni"),
    'G' to listOf("Gaurav", "Geeta", "Girish", "Gunjan", "Govind"),
    'H' to listOf("Harsh", "Hema", "Hemant", "Heena", "Harish"),
    'I' to listOf("Isha", "Iqbal", "Indu", "Ishaan", "Imran"),
    'J' to listOf("Jayant", "Jyoti", "Jatin", "Jaya", "Jaspreet"),
    'K' to listOf("Kiran", "Kunal", "Kavita", "Komal", "Krishna"),
    'L' to listOf("Lakshmi", "Lalit", "Leela", "Lokesh", "Lata"),
    'M' to listOf("Manish", "Meena", "Mohan", "Mitali", "Mukesh"),
    'N' to listOf("Nitin", "Neha", "Nikhil", "Namrata", "Narayan"),
    'O' to listOf("Omkar", "Ojas", "Oviya", "Onkar", "Omisha"),
    'P' to listOf("Prakash", "Pooja", "Pankaj", "Priya", "Parth"),
    'Q' to listOf("Qadir", "Qamar", "Quasar", "Qiana", "Qutub"),
    'R' to listOf("Ramesh", "Ritika", "Rajesh", "Rohit", "Rupali"),
    'S' to listOf("Suresh", "Sunita", "Sanjay", "Sneha", "Siddharth"),
    'T' to listOf("Tarun", "Tanvi", "Tejas", "Tina", "Tushar"),
    'U' to listOf("Usha", "Udit", "Uma", "Utkarsh", "Unnati"),
    'V' to listOf("Vikas", "Varun", "Vandana", "Vinod", "Vaishali"),
    'W' to listOf("Wasim", "William", "Wahid", "Wamiqa", "Winston"),
    'X' to listOf("Xavier", "Xena", "Xander", "Xyla", "Xavion"),
    'Y' to listOf("Yogesh", "Yamini", "Yuvraj", "Yash", "Yashika"),
    'Z' to listOf("Zoya", "Zakir", "Zubair", "Zarina", "Zeeshan")
)