package com.charlesmuchene.kotlin.learn.business

import androidx.lifecycle.ViewModel
import com.charlesmuchene.kotlin.learn.models.Country
import com.charlesmuchene.kotlin.learn.models.Currency
import com.charlesmuchene.kotlin.learn.models.Language
import com.charlesmuchene.kotlin.learn.utilities.orDash

/**
 * Country details view model
 */
class CountryDetailsViewModel : ViewModel() {

    /**
     * Get formatted country details
     *
     * @param country [Country] instance
     * @return Formatted country details
     */
    fun getDetails(country: Country): String = with(country) {
        """
        Native Name: $nativeName

        Demonym: ${demonym.orDash}

        Population: ${String.format("%,d", population)}

        Language(s): ${languages.joinToString(transform = Language::toString)}

        Currency: ${currencies.joinToString(transform = Currency::toString)}

        Timezone(s): ${timezones.joinToString()}
    """.trimIndent()
    }
}