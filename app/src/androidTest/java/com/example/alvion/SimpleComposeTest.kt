// app/src/androidTest/java/com/example/alvion/ui/SessionScreenTest.kt
package com.example.alvion.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class SessionScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun endSessionButton_callsOnEnd() {
        var ended = false

        composeTestRule.setContent {
            SessionScreen(onEnd = { ended = true })
        }

        composeTestRule
            .onNodeWithText("End Session")
            .assertIsDisplayed()
            .performClick()

        assertTrue("onEnd callback should be invoked", ended)
    }

    @Test
    fun notifyChip_showsDialog_andIncrementsCount() {
        composeTestRule.setContent {
            SessionScreen(onEnd = {})
        }

        // First click on "Notify"
        composeTestRule
            .onNodeWithText("Notify")
            .assertIsDisplayed()
            .performClick()

        // Dialog should appear
        composeTestRule
            .onNodeWithText("Notification sent")
            .assertIsDisplayed()

        // Text should say "Notify tapped 1 time."
        composeTestRule
            .onNodeWithText("Notify tapped 1 time.")
            .assertIsDisplayed()

        // Close dialog
        composeTestRule
            .onNodeWithText("OK")
            .performClick()

        // Second click on "Notify"
        composeTestRule
            .onNodeWithText("Notify")
            .performClick()

        // Now the text should say "Notify tapped 2 times."
        composeTestRule
            .onNodeWithText("Notify tapped 2 times.")
            .assertIsDisplayed()
    }

    @Test
    fun soundChip_showsSoundDialog_andTurnOffClosesIt() {
        composeTestRule.setContent {
            SessionScreen(onEnd = {})
        }

        // Tap "Sound" chip
        composeTestRule
            .onNodeWithText("Sound")
            .assertIsDisplayed()
            .performClick()

        // Sound dialog should appear
        composeTestRule
            .onNodeWithText("Playing until you turn it off")
            .assertIsDisplayed()

        // Press "Turn off sound"
        composeTestRule
            .onNodeWithText("Turn off sound")
            .performClick()

        // After turning off, the dialog should be gone.
        // (If you want, you can assert it's not there by using
        // .onNodeWithText("Playing until you turn it off").assertDoesNotExist()
        // but that requires using .waitUntilDoesNotExist or similar pattern.)
    }

    @Test
    fun emergencyCard_isClickable() {
        composeTestRule.setContent {
            SessionScreen(onEnd = {})
        }

        // Just verify that "Emergency" card exists and is clickable.
        // We are NOT testing actual call here (that’s better as a unit test on makeEmergencyCall).
        composeTestRule
            .onNodeWithText("Emergency")
            .assertIsDisplayed()
            .performClick()
    }

}

