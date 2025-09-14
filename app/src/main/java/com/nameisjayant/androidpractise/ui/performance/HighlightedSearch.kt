package com.nameisjayant.androidpractise.ui.performance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.androidpractise.ui.theme.fontMedium


@Composable
fun HighlightedSearch(
    modifier: Modifier = Modifier
) {
    var search by remember { mutableStateOf("") }

    val buildAnnotatedString = buildAnnotatedString {
        if (search.isBlank())
            append(text)
        else {
            val regexPattern = Regex(search, RegexOption.IGNORE_CASE)
            var endIndex = 0
            regexPattern.findAll(text).forEach { res ->
                val start = res.range.first
                val end = res.range.last + 1
                append(text.substring(endIndex, start))

                withStyle(
                    style = SpanStyle(
                        background = Color.Yellow,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(text.substring(start, end))
                }
                endIndex = end
            }
            if (endIndex < text.length)
                append(text.substring(endIndex))
        }
    }

    Column(
        modifier = modifier
            .padding(
                16.dp
            )
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = search,
            onValueChange = {
                search = it
            },
            placeholder = {
                Text(text = "Search")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            textStyle = TextStyle(
                fontFamily = fontMedium
            )
        )
        LazyColumn {
            item {
                Text(
                    buildAnnotatedString, style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = fontMedium,
                        lineHeight = 26.sp
                    )
                )
            }
        }
    }
}

val text =
    "The concept of lifelong learning has gained significant importance in today’s fast-paced world where technology, social systems, and professional demands evolve at a rate never seen before in human history. Lifelong learning refers to the continuous, voluntary, and self-motivated pursuit of knowledge for personal or professional development. Unlike traditional learning, which was often confined to the classroom and associated primarily with childhood and early adulthood, lifelong learning emphasizes that education does not stop with a degree. Instead, it is an ongoing process that can and should continue throughout one’s life. This perspective is especially relevant in the digital age where knowledge is constantly being updated, and the skills that were valuable yesterday may not necessarily hold the same weight tomorrow. For instance, advancements in artificial intelligence, automation, and data science are transforming entire industries, creating new opportunities while also rendering certain job roles obsolete. In such a dynamic environment, individuals who adopt the mindset of continuous learning are more adaptable, resilient, and capable of thriving in the face of change. Beyond the workplace, lifelong learning also contributes to personal growth by encouraging curiosity, creativity, and critical thinking. It empowers individuals to explore new hobbies, gain cultural awareness, and develop stronger communication skills. For example, someone might take up painting, learn a musical instrument, or start coding as a hobby, all of which can enrich their lives and broaden their perspectives. Moreover, lifelong learning fosters social connections, as people often engage with communities, workshops, and online platforms to share knowledge and experiences. This sense of belonging and collaboration not only enhances learning outcomes but also nurtures emotional well-being. Governments and organizations are increasingly recognizing the value of lifelong learning by promoting policies and programs that encourage skill development across all ages. Online platforms, open-source content, and virtual classrooms have made it easier than ever to access resources on virtually any topic, reducing barriers related to geography, cost, and time. The rise of micro-learning modules, podcasts, and short certification courses illustrates how flexible education has become to meet the needs of modern learners. Yet, lifelong learning is not without its challenges. Maintaining motivation, managing time effectively, and overcoming the fear of failure can all act as barriers. However, by cultivating a growth mindset—believing that intelligence and abilities can be developed through effort—individuals can overcome these obstacles and fully embrace continuous learning. The rewards are immense, ranging from improved career prospects and financial security to enhanced mental agility and personal satisfaction. In the end, lifelong learning is not just a necessity for navigating the complexities of modern life, but also a fulfilling journey that enriches both the mind and the soul. By committing to this path, individuals can remain relevant in their careers, foster deeper human connections, and unlock new dimensions of personal growth that make life more meaningful and rewarding."