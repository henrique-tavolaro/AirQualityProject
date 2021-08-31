package com.example.airqualityproject.presenter.detail_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.airqualityproject.R
import com.example.airqualityproject.databinding.FragmentDetailBinding
import com.example.airqualityproject.presenter.AirViewModel
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

    @SuppressLint("ResourceAsColor")
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

        val o3card = binding.o3
        val pm10card = binding.pm10
        val pm25card = binding.pm25
        val uvicard = binding.uvi

        viewModel.selected.observe(viewLifecycleOwner, Observer {
            if (it == Pollutants.O3.name) {
                o3card.setCardBackgroundColor(R.color.teal_700)
            } else {
                o3card.setCardBackgroundColor(R.color.cardview_dark_background)
            }
            if (it == Pollutants.PM10.name) {
                pm10card.setCardBackgroundColor(R.color.teal_700)
            } else {
                pm10card.setCardBackgroundColor(R.color.cardview_dark_background)
            }
            if (it == Pollutants.PM25.name) {
                pm25card.setCardBackgroundColor(R.color.teal_700)
            } else {
                pm25card.setCardBackgroundColor(R.color.cardview_dark_background)
            }
            if (it == Pollutants.UVI.name) {
                uvicard.setCardBackgroundColor(R.color.teal_700)
            } else {
                uvicard.setCardBackgroundColor(R.color.cardview_dark_background)
            }
        })


        val aaChartView = binding.aaChartView
        viewModel.getDetails("@${args.data.uid}")


        o3card.setOnClickListener {
            viewModel.pollutantSelected.value = Pollutants.O3.name
            viewModel.getDetails("@${args.data.uid}")
        }

        pm10card.setOnClickListener {
            viewModel.pollutantSelected.value = Pollutants.PM10.name
            viewModel.getDetails("@${args.data.uid}")
        }

        pm25card.setOnClickListener {
            viewModel.pollutantSelected.value = Pollutants.PM25.name
            viewModel.getDetails("@${args.data.uid}")
        }

        uvicard.setOnClickListener {
            viewModel.pollutantSelected.value = Pollutants.UVI.name
            viewModel.getDetails("@${args.data.uid}")
        }

        viewModel.detailsResult.observe(viewLifecycleOwner, Observer {
            val result = it

            val createChart = viewModel.createChart(result!!)
            aaChartView.aa_drawChartWithChartOptions(createChart)

            val callback = OnMapReadyCallback { googleMap ->
                val lat = args.data.station.geo[0]
                val lon = args.data.station.geo[1]
                val location = LatLng(lat, lon)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 14f))
                googleMap.addMarker(
                    MarkerOptions()
                        .position(location)
                        .title(result.data.attributions[0].name)
                        .snippet(result.data.city.name)
                )
            }

            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
            mapFragment?.getMapAsync(callback)

        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val toolbar : Toolbar = binding.myToolbar

    }
}


enum class Pollutants {
    O3,
    PM10,
    PM25,
    UVI
}




