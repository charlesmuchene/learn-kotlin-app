package com.charlesmuchene.kotlin.learn.views.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.charlesmuchene.kotlin.learn.R
import com.charlesmuchene.kotlin.learn.models.Country
import com.charlesmuchene.kotlin.learn.utilities.OnItemClickListener
import com.charlesmuchene.kotlin.learn.utilities.promote

/**
 * Country Adapter
 */
class CountryAdapter : RecyclerView.Adapter<CountryViewHolder>() {

    private val countries = mutableListOf<Country>()

    private var listener: OnItemClickListener<Country>? = null

    /**
     * Set on item click listener to the given listener
     *
     * @param listener [OnItemClickListener] instance
     */
    fun setItemListener(listener: OnItemClickListener<Country>) {
        this.listener = listener
    }

    /**
     * Set countries
     *
     * @param countries [List]
     */
    fun setCountries(countries: List<Country>) {
        this.countries.clear()
        this.countries.addAll(countries)
        this.countries.promote()
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