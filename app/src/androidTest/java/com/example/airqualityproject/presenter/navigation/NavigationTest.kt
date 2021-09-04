package com.example.airqualityproject.presenter.navigation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.airqualityproject.R
import com.example.airqualityproject.domain.repositories.FakeAirRepository
import com.example.airqualityproject.presenter.AirViewModel
import com.example.airqualityproject.presenter.home_fragment.HomeFragment
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class NavigationTest {

    private val DUMMY_TOKEN = "1234"

    private val modules = module {
        val repository = FakeAirRepository()
        viewModel { AirViewModel(repository, DUMMY_TOKEN) }
    }


    @Test
    fun clickOnFirstItemOfRecyclerView_navigateToDetailsScreen() {

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        loadKoinModules(modules)
        // Create a graphical FragmentScenario for the TitleScreen
        launchFragmentInContainer() {
            HomeFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        // The fragment’s view has just been created
                        navController.setGraph(R.navigation.main_graph)
                        Navigation.setViewNavController(fragment.requireView(), navController)
                    }
                }
            }
        }


        Espresso.onView(ViewMatchers.withId(R.id.et_search))
            .perform(ViewActions.typeText("honolulu"))
            .perform(ViewActions.pressImeActionButton())
        Espresso.onView(ViewMatchers.withId(R.id.rv_home))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click())
            )

        assert(navController.currentDestination?.id == R.id.detailFragment)

    }

    @Test
    fun clickOnSecondItemOfRecyclerView_navigateToDetailsScreen() {

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        loadKoinModules(modules)
        // Create a graphical FragmentScenario for the TitleScreen
        launchFragmentInContainer() {
            HomeFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        // The fragment’s view has just been created
                        navController.setGraph(R.navigation.main_graph)
                        Navigation.setViewNavController(fragment.requireView(), navController)
                    }
                }
            }
        }


        Espresso.onView(ViewMatchers.withId(R.id.et_search))
            .perform(ViewActions.typeText("honolulu"))
            .perform(ViewActions.pressImeActionButton())
        Espresso.onView(ViewMatchers.withId(R.id.rv_home))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click())
            )

        assert(navController.currentDestination?.id == R.id.detailFragment)

    }



}