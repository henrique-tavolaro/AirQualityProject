package com.example.airqualityproject.presenter.detail_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.airqualityproject.R
import com.example.airqualityproject.databinding.FragmentDetailBinding
import com.example.airqualityproject.domain.model.details.ResponseDetails
import com.example.airqualityproject.presenter.AirViewModel
import com.github.aachartmodel.aainfographics.aachartcreator.*
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAChart
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AATitle
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAXAxis
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {
    val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: AirViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )
        var selected = ""

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        val aaChartView = binding.aaChartView


        binding.o3.setOnClickListener {
            viewModel.selected = Pollutants.O3.name
            viewModel.getDetails("@${args.data.uid}")
        }

        binding.pm10.setOnClickListener {
            viewModel.selected = Pollutants.PM10.name
            viewModel.getDetails("@${args.data.uid}")
        }

        binding.pm25.setOnClickListener {
            viewModel.selected = Pollutants.PM25.name
            viewModel.getDetails("@${args.data.uid}")
        }

        binding.uvi.setOnClickListener {
            viewModel.selected = Pollutants.UVI.name
            viewModel.getDetails("@${args.data.uid}")
        }

        viewModel.detailsResult.observe(viewLifecycleOwner, Observer {
            val createChart = viewModel.createChart(it!!)
            aaChartView.aa_drawChartWithChartOptions(createChart)
        })
        return binding.root
    }

    private val callback = OnMapReadyCallback { googleMap ->
        val lat = args.data.station.geo[0]
        val lon = args.data.station.geo[1]
        val location = LatLng(lat, lon)
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 14f))
        googleMap.addMarker(MarkerOptions().position(location).title(args.data.station.name))
    }
}


enum class Pollutants {
    O3,
    PM10,
    PM25,
    UVI
}


