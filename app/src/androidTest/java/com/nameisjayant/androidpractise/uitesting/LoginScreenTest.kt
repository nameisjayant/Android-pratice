package com.nameisjayant.androidpractise.uitesting

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nameisjayant.androidpractise.ui.theme.AndroidPractiseTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun loginScree() {
        composeTestRule.setContent {
            AndroidPractiseTheme {
                LoginScreen()
            }
        }
    }

    @Test
    fun login_initial_ui_exits() {
        // Check that the username text field shows its placeholder text
        composeTestRule.onNodeWithText("Enter Username").assertIsDisplayed()
        // Check that the password text field shows its placeholder text
        composeTestRule.onNodeWithText("Enter Password").assertIsDisplayed()
        // Check that the login button is visible
        composeTestRule.onNodeWithText("Login").assertIsDisplayed()
    }

    @Test
    fun enter_text_on_the_text_fields() {
        val username = "nameisjayant"
        composeTestRule.onNodeWithText("Enter Username").performTextInput(username)
        val password = "123443"
        composeTestRule.onNodeWithText("Enter Password").performTextInput(password)
    }

    @Test
    fun login_button_clickable(){
        composeTestRule.onNodeWithText("Login").performClick()
        composeTestRule.onNodeWithText("Login").assertHasClickAction()
    }
}