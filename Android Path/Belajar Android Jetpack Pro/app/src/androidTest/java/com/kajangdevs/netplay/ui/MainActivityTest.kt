package com.kajangdevs.netplay.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.kajangdevs.netplay.R
import com.kajangdevs.netplay.utils.DataDummy
import com.kajangdevs.netplay.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val dummyHastag = DataDummy.generateDummyHastag()

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadHastag() {
        onView(withId(R.id.rv_hastag)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_hastag)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyHastag.size
            )
        )
    }

    @Test
    fun loadPopularMovieAndDetail() {
        onView(withId(R.id.rv_popular_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_popular_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.ic_bookmarkMv)).check(matches(isDisplayed()))
    }

    @Test
    fun loadPopularTvSHowAndDetail() {
        onView(withId(R.id.rv_popular_tvshow)).perform(scrollTo()).check(matches(isDisplayed()))
        onView(withId(R.id.rv_popular_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.ic_bookmarkTv)).check(matches(isDisplayed()))
    }


    @Test
    fun loadFragmentMoviesAndDetail() {
        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.ic_bookmarkMv)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFragmentTvSHowAndDetail() {
        onView(withId(R.id.navigation_tvshow)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.ic_bookmarkTv)).check(matches(isDisplayed()))
    }

}