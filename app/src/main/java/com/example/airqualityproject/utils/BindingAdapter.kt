package com.example.airqualityproject.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.airqualityproject.domain.model.Data

@BindingAdapter("set_city")
fun TextView.setCity(item: Data?){
    item?.let {
        text = item.station.name
    }
}

@BindingAdapter("set_country")
fun TextView.setCountry(item: Data?){
    item?.let {
        text = item.station.country
    }
}