package com.charlesmuchene.kotlin.learn.views.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.charlesmuchene.kotlin.learn.R
import com.charlesmuchene.kotlin.learn.models.Country
import com.charlesmuchene.kotlin.learn.utilities.OnItemClickListener

/**
 * Country Adapter
 */
class CountryAdapter(private val listener: OnItemClickListener<Country>) :
    RecyclerView.Adapter<CountryViewHolder>() {

    private val countries = mutableListOf<Country>()

    /**
     * Set countries
     *
     * @param countries [List]
     */
    fun setCountries(countries: List<Country>) {
        this.countries.clear()
        this.countries.addAll(countries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.country_item_layout, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position], listener)
    }
}