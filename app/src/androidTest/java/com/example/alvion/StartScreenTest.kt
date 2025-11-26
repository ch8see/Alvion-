// app/src/androidTest/java/com/example/alvion/ui/StartScreenTest.kt
package com.example.alvion.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class StartScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun startButton_isDisplayed_andCallsOnStart() {
        var started = false

        // Set the StartScreen composable
        composeRule.setContent {
            StartScreen(onStart = { started = true })
        }

        // Find the "Start Session" button by its text,
        // ensure it's visible, then click it
        composeRule
            .onNodeWithText("Start Session")
            .assertIsDisplayed()
            .performClick()

        // Verify that the callback was invoked
        assertTrue("onStart should be called when Start Session is clicked", started)
    }
}
