package com.charlesmuchene.kotlin.learn.viewmodels

import androidx.lifecycle.MutableLiveData
import com.charlesmuchene.kotlin.learn.data.Failure
import com.charlesmuchene.kotlin.learn.data.Success
import com.charlesmuchene.kotlin.learn.models.Country
import com.charlesmuchene.kotlin.learn.utilities.Configuration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Country view model
 */
class CountryViewModel : ScopedViewModel() {

    val countryFailure = MutableLiveData<Failure>()
    val countrySuccess = MutableLiveData<Success<List<Country>>>()

    /**
     * Fetch all countries from db or api
     */
    fun fetchCountries() {

        launch(Dispatchers.IO) {
            val countries = Configuration.db.countryDao().getAllAsync()
            if (countries.isEmpty()) fetchCountriesFromApi()
            else withContext(Dispatchers.Main) {
                reportSuccessLoadingCountries(countries)
            }
        }

    }

    /**
     * Fetch countries from api
     */
    private suspend fun fetchCountriesFromApi() {
        try {
            val list = Configuration.apiService.getCountries().body()
            list?.let(Configuration.db.countryDao()::insertAll)
            withContext(Dispatchers.Main) {
                list?.let(::reportSuccessLoadingCountries)
                    ?: reportErrorLoadingCountries(Throwable("Error loading countries"))
            }
        } catch (t: Throwable) {
            withContext(Dispatchers.Main) {
                reportErrorLoadingCountries(t)
            }
        }
    }

    /**
     * Report error during fetch
     *
     * @param throwable [Throwable] instance
     */
    private fun reportErrorLoadingCountries(throwable: Throwable) {
        countryFailure.value = Failure(throwable)
    }

    /**
     * Report success during load
     *
     * @param countries [List]
     */
    private fun reportSuccessLoadingCountries(countries: List<Country>) {
        countrySuccess.value = Success(countries)
    }
}