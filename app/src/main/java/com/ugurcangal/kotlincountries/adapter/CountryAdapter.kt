package com.ugurcangal.kotlincountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ugurcangal.kotlincountries.databinding.ItemCountryBinding
import com.ugurcangal.kotlincountries.model.CountryModel
import com.ugurcangal.kotlincountries.util.downloadFromUrl
import com.ugurcangal.kotlincountries.util.placeholderProgressBar
import com.ugurcangal.kotlincountries.view.FeedFragmentDirections

class CountryAdapter(private val countryList: ArrayList<CountryModel>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.binding.name.text = countryList[position].countryName
        holder.binding.region.text = countryList[position].countryRegion
        holder.binding.imageView.downloadFromUrl(countryList[position].imageUrl,
            placeholderProgressBar(holder.itemView.context))

        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }


    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList : List<CountryModel>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged() // adapter'ü yenilemek için kullandığımız bir method.
    }
}