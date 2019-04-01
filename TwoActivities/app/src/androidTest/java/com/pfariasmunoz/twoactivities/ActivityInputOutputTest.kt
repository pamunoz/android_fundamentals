package com.pfariasmunoz.twoactivities



import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
//import androidx.test.runner.AndroidJUnit4
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ActivityInputOutputTest {

    @get:Rule
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.pfariasmunoz.twoactivities", appContext.packageName)
    }

    @Test fun activityLaunch() {
        onView(withId(R.id.btn_main)).perform(click())
        onView(withId(R.id.tv_second_message)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_second_reply)).perform(click())
        onView(withId(R.id.tv_text_header_reply)).check(matches(isDisplayed()))
    }
}
