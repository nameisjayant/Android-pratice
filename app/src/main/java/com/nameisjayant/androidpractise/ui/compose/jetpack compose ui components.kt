package com.nameisjayant.androidpractise.ui.compose

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BasicTooltipBox
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberBasicTooltipState
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun CrossFadeScreen(
    modifier: Modifier = Modifier
) {
    var show by remember {
        mutableStateOf((false))
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Crossfade(
            show, animationSpec = tween(
                durationMillis = 2000
            )
        ) {
            when (it) {
                true -> {
                    Text("Text Data")
                }

                else -> {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color.Red)
                    )
                }
            }
        }
        Button(onClick = {
            show = !show
        }) {
            Text("Show")
        }
    }

}

@Composable
fun PagerScreen(modifier: Modifier = Modifier) {

    var pagerState = rememberPagerState {
        3
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize(),

        ) {
        Text("Text $it", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SwipeRefreshScreen(modifier: Modifier = Modifier) {
    SharedTransitionLayout {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogPropertyScreen(modifier: Modifier = Modifier) {

    BasicAlertDialog(
        modifier = modifier,
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        // content
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TriStateCheckBoxScreen(
    modifier: Modifier = Modifier
) {
    var isRefreshing by remember {
        mutableStateOf(false)
    }
    val state = rememberPullToRefreshState()
    val scope = rememberCoroutineScope()
    PullToRefreshBox(
        modifier = modifier.fillMaxWidth(),
        state = state,
        isRefreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            scope.launch {
                delay(5000)
                // call function to execute
                isRefreshing = false
            }
        },
        contentAlignment = Alignment.Center
    ) {
        Text("Content")
    }
}

@Composable
fun TextFieldFocusScreen(modifier: Modifier = Modifier) {

    var name by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.focusRequester(focusRequester)
        )
    }
}

@Composable
fun ClickableTextScreen(modifier: Modifier = Modifier) {

    val uriHandler = LocalUriHandler.current

    val text = buildAnnotatedString {
        append("Build better apps with ")
        withLink(
            LinkAnnotation.Url(
                url = "https://developer.android.com/jetpack/compose",
                styles = TextLinkStyles(
                    style = SpanStyle(
                        color = Color.Red,
                        textDecoration = TextDecoration.Underline
                    )
                ),
                linkInteractionListener = {
                    // on click
                    val url = (it as LinkAnnotation.Url).url
                    uriHandler.openUri(url)
                }
            ),
        ) {
            append("Jetpack Compose")
        }
    }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        BasicText(
            text = text
        )
    }

}


@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun TooltipScreen(modifier: Modifier = Modifier) {
    val state = rememberBasicTooltipState()
    val scope = rememberCoroutineScope()
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        BasicTooltipBox(
            state = state,
            tooltip = {
                Text("Information")
            },
            positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider()
        ) {
            IconButton(
                onClick = {
                    scope.launch {
                        state.show(MutatePriority.Default)
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Info",
                    tint = Color.Red
                )
            }

        }
    }
}

@Composable
fun BackHandlerScreen(modifier: Modifier = Modifier) {

    BackHandler {
        // perform whatever on back press
    }
}
