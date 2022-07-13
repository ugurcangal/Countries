package com.ugurcangal.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ugurcangal.kotlincountries.R
import com.ugurcangal.kotlincountries.databinding.FragmentCountryBinding
import com.ugurcangal.kotlincountries.util.downloadFromUrl
import com.ugurcangal.kotlincountries.util.placeholderProgressBar
import com.ugurcangal.kotlincountries.viewmodel.CountryViewModel


class CountryFragment : Fragment() {
    private lateinit var viewModel: CountryViewModel
    private var _binding: FragmentCountryBinding? = null
    private val binding get() = _binding!!
    private var countryUuid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCountryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }
        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)
        observeLiveData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

   private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                binding.countryName.text = country.countryName
                binding.countryCapital.text = country.countryCapital
                binding.countryCurrency.text = country.countryCurrency
                binding.countryLanguage.text = country.countryLanguage
                binding.countryRegion.text = country.countryRegion
                context?.let {
                    binding.countryImage.downloadFromUrl(country.imageUrl, placeholderProgressBar(it))
                }


            }
        })
    }
}