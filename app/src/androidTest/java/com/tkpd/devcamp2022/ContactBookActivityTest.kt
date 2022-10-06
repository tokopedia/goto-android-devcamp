package com.tkpd.devcamp2022

import android.Manifest
import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.anyIntent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.ui.ContactBookActivity
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.ui.ContactDetailActivity
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.util.EspressoIdlingResource
import com.tkpd.devcamp2022.day4.unit_test_instrument_test.presentation.viewholder.ContactItemViewHolder
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.core.IsNot
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class ContactBookActivityTest {

    @get:Rule
    var mRuntimePermissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(Manifest.permission.READ_CONTACTS)

    // ActivityTestRule: If you are passing a custom intent, change 3rd param (launchActivity) false
    @get:Rule
    var mActivityRule = ActivityTestRule(ContactBookActivity::class.java, false, true)

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
        Intents.init()
        Intents.intending(anyIntent())
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
        Intents.release()
    }

    @Test
    fun validate_contact_book() {
        contact_book_shows_all_main_components()
        contact_item_click_should_navigate_to_detail()
    }

    private fun contact_book_shows_all_main_components() {
        onView(withId(R.id.tv_contact_book_title)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_contact_book_items)).check(matches(isDisplayed()))
    }

    private fun contact_item_click_should_navigate_to_detail() {
        contactBookItem_clickOnPosition(0)
        intended(hasComponent(ContactDetailActivity::class.java.name))

//        contactBookItem_clickContactWithName("Irfan")
//        intended(hasComponent(ContactDetailActivity::class.java.name))
    }

    private fun contactBookItem_clickOnPosition(position: Int) {
        val viewInteraction = onView(withId(R.id.rv_contact_book_items)).check(matches(isDisplayed()))
        viewInteraction.perform(
            RecyclerViewActions.actionOnItemAtPosition<ContactItemViewHolder>(
                position,
                ViewActions.click()
            )
        )
    }

    private fun contactBookItem_clickContactWithName(name: String) {
        onView(withId(R.id.rv_contact_book_items)).check(matches(isDisplayed()))
        onView(allOf(withText(name), withId(R.id.tv_contact_name))).check(matches(isDisplayed()))
    }
}