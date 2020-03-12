package com.charlesmuchene.kotlin.learn.views.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.charlesmuchene.kotlin.learn.R
import com.charlesmuchene.kotlin.learn.databinding.CountryItemLayoutBinding
import com.charlesmuchene.kotlin.learn.models.Country
import com.charlesmuchene.kotlin.learn.utilities.OnItemClickListener

/**
 * Country Adapter
 *
 * @param listener [OnItemClickListener] instance
 */
class CountryAdapter(private val listener: OnItemClickListener<Country>) :
    ListAdapter<Country, CountryViewHolder>(CountryItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding =
            CountryItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}