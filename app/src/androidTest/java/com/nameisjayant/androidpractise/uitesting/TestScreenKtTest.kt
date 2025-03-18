package com.nameisjayant.androidpractise.uitesting

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nameisjayant.androidpractise.ui.theme.AndroidPractiseTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Before
    fun testScreen() {
        composeTestRule.setContent {
            AndroidPractiseTheme {
                TestScreen()
            }
        }
        composeTestRule.onRoot().printToLog("Main")
    }

    @Test
    fun testSubmitButton() {
//        composeTestRule.onNodeWithText("Submit").performClick()
//        composeTestRule.onNodeWithText("Submit").isDisplayed()
//        composeTestRule.onNodeWithText("Submit").assertHasClickAction()
//        composeTestRule.onNodeWithText("Submit").assertTextEquals("Submit")

        // composeTestRule.onNodeWithContentDescription("Submit Button").performClick()
        composeTestRule.onNodeWithTag("").performClick()
    }
}