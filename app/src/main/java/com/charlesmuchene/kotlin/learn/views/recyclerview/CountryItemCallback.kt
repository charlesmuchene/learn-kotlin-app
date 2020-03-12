package com.charlesmuchene.kotlin.learn.views.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.charlesmuchene.kotlin.learn.models.Country

/**
 * Country item callback
 */
object CountryItemCallback : DiffUtil.ItemCallback<Country>() {

    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }
}