package com.example.airqualityproject

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

fun showListOfCities(position: Int, city: String): Matcher<in View> {
    return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java){
        override fun describeTo(description: Description?) {
            description!!
                .appendText("View com cidade")
                .appendValue(city)
                .appendText("na posição")
                .appendValue(position)
                .appendText("não foi encontrada")
        }

        override fun matchesSafely(item: RecyclerView?): Boolean {
            val viewHolder = item!!.findViewHolderForAdapterPosition(position)!!.itemView
            val cityText = viewHolder.findViewById<TextView>(R.id.tv_city)
            val expected : Boolean = cityText.text.toString() == city
            return expected
        }

    }
}