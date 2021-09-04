package com.example.airqualityproject.presenter.home_fragment

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.airqualityproject.R
import com.example.airqualityproject.databinding.FragmentHomeBinding
import com.example.airqualityproject.presenter.AirViewModel
import com.example.airqualityproject.presenter.CityListAdapter
import com.example.airqualityproject.presenter.CityListListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val viewModel: AirViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)


        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        val editText = binding.etSearch
        val adapter = CityListAdapter(CityListListener { id ->
            viewModel.onCityClicked(id)
        })

        binding.rvHome.adapter = adapter

        viewModel.toastMessage.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            val progressBar = binding.progressCircular
            if(it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        viewModel.result.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { response ->
                val noResults = binding.tvNoResults
                if(response.data.isNotEmpty()){
                    adapter.submitList(response.data)
                    noResults.visibility = View.GONE
                } else {
                    noResults.visibility = View.VISIBLE
                }

            }
        })

        viewModel.navigateToAirQualityDetails.observe(viewLifecycleOwner, Observer {
           it?.let{
               this.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
               )
               editText.setText("")
               viewModel.onCityNavigated()
           }
        }, )

        editText.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    viewModel.search(editText.text.toString())

                    editText.hideKeyboard()
                    editText.clearFocus()
                    return true
                }
                return false
            }
        })

        return binding.root
    }
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
