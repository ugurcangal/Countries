package com.ugurcangal.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ugurcangal.kotlincountries.adapter.CountryAdapter
import com.ugurcangal.kotlincountries.databinding.FragmentFeedBinding
import com.ugurcangal.kotlincountries.viewmodel.FeedViewModel


class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf()) // RecyclerView adapter

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        binding.countryListRecycler.layoutManager = LinearLayoutManager(context)
        binding.countryListRecycler.adapter = countryAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.countryListRecycler.visibility = View.GONE
            binding.countryError.visibility = View.GONE
            binding.countryLoading.visibility = View.VISIBLE
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.refreshData()
        }
        observeLiveData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

   private fun observeLiveData() {
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                binding.countryListRecycler.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    binding.countryError.visibility = View.VISIBLE
                    binding.countryListRecycler.visibility = View.GONE
                } else {
                    binding.countryError.visibility = View.GONE
                }
            }

        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    binding.countryLoading.visibility = View.VISIBLE
                    binding.countryListRecycler.visibility = View.GONE
                    binding.countryError.visibility = View.GONE
                }else{
                    binding.countryLoading.visibility = View.GONE
                }
            }

        })
    }
}