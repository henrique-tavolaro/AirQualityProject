package com.example.airqualityproject.presenter.home_fragment

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import com.example.airqualityproject.R
import com.example.airqualityproject.domain.repositories.FakeAirRepository
import com.example.airqualityproject.domain.repositories.response
import com.example.airqualityproject.presenter.AirViewModel
import com.example.airqualityproject.presenter.detail_fragment.DetailFragment
import com.example.airqualityproject.showListOfCities
import org.junit.Before
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class HomeFragmentTest {

    private val DUMMY_TOKEN = "1234"

    private val context = InstrumentationRegistry.getInstrumentation().context

    private val modules = module {
        val repository = FakeAirRepository()
        viewModel { AirViewModel(repository, DUMMY_TOKEN) }
    }

    @Before
    fun setup(){
        loadKoinModules(modules)
        launchFragmentInContainer<HomeFragment>()
    }


    @Test
    fun shouldDisplayRecyclerViewWithResults() {
        onView(withId(R.id.et_search)).perform(typeText("honolulu"))
            .perform(pressImeActionButton())
        onView(withId(R.id.rv_home)).check(
            matches(
                showListOfCities(
                    0,
                    "honolulu; Sand Island, Hawaii, USA"
                )
            )
        )
        onView(withId(R.id.rv_home)).check(
            matches(
                showListOfCities(
                    1,
                    "honolulu; Honolulu, Hawaii, USA"
                )
            )
        )
    }



    @Test
    fun shouldDisplayViewWithZeroResults() {
        onView(withId(R.id.et_search)).perform(typeText("honolu"))
            .perform(pressImeActionButton())
        onView(withId(R.id.tv_no_results)).check(matches(isDisplayed()))
    }

//    @Test
//    fun shouldDisplayToastOfNetworkError(){
//        onView(withId(R.id.et_search)).perform(typeText("network error"))
//            .perform(pressImeActionButton())
//
//        assertThat("Error fetching data",check(matches(isDisplayed())) )
//
//
//    }

}