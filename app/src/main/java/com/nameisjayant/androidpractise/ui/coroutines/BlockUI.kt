package com.nameisjayant.androidpractise.ui.coroutines

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import java.util.concurrent.Executors


@Composable
fun BlockUIThroughThread(
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Button(onClick = {
                Thread.sleep(5000) // UI blocked for 5 secs
            }) {
                Text(text = "Click")
            }
        }
        items(100) {
            Text(text = "Text $it")
        }
    }
}

suspend fun launchFunction() = runBlocking {
    val result = async {
        delay(1000)
        "Hello from async"
    }
}

@Composable
fun BlockFreeUIThroughCoroutines(
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Button(onClick = {
                scope.launch {
                    delay(5000)
                }
            }) {
                Text(text = "Click")
            }
        }
        items(100) {
            Text(text = "Text $it")
        }
    }
}


@Composable
fun BlockFreeUIThroughThread(
    modifier: Modifier = Modifier
) {
    val executor = Executors.newSingleThreadExecutor()
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Button(onClick = {
                Thread {
                    Thread.sleep(5000)
                    Handler(Looper.getMainLooper()).post {
                        // UI Operations
                    }
                }
                executor.execute {
                    Thread.sleep(5000)
                }

            }) {
                Text(text = "Click")
            }
        }
        items(100) {
            Text(text = "Text $it")
        }
    }
}

val handler = CoroutineExceptionHandler { _, exception ->
    println("Caught exception: $exception")
}

fun main() = runBlocking {
    val job = launch(handler) {
        throw IllegalArgumentException("Something went wrong!")
    }
    job.join()
    runBlocking {
        withTimeoutOrNull(5000){

        }
    }
}