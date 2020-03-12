package com.charlesmuchene.kotlin.learn.viewmodels

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.charlesmuchene.kotlin.learn.data.ApiResponse
import com.charlesmuchene.kotlin.learn.data.Failure
import com.charlesmuchene.kotlin.learn.data.Success
import com.charlesmuchene.kotlin.learn.models.Country
import com.charlesmuchene.kotlin.learn.utilities.Configuration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Country view model
 */
class CountryViewModel : ViewModel() {

    private val countryLiveData = MutableLiveData<ApiResponse>()

    /**
     * Get country live data
     *
     * @return [LiveData] instance
     */
    fun getCountryLiveData(): LiveData<ApiResponse> = countryLiveData

    /**
     * Fetch all countries from db or api
     */
    fun fetchCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            val countries = Configuration.db.countryDao().getAll()
            if (countries.isEmpty()) fetchCountriesFromApi()
            else reportSuccessLoadingCountries(countries)
        }
    }

    /**
     * Fetch countries from api
     */
    private suspend fun fetchCountriesFromApi() {
        try {
            val listOfCountries = Configuration.apiService.getCountries().body()
            if (listOfCountries != null) {
                Configuration.db.countryDao().insertAll(listOfCountries)
                reportSuccessLoadingCountries(listOfCountries)
            } else {
                reportErrorLoadingCountries(Throwable("Error loading countries"))
            }
        } catch (t: Throwable) {
            reportErrorLoadingCountries(t)
        }
    }

    /**
     * Report error after countries fetch
     *
     * @param throwable [Throwable] instance
     */
    @WorkerThread
    private fun reportErrorLoadingCountries(throwable: Throwable) {
        countryLiveData.postValue(Failure(throwable))
    }

    /**
     * Report success after countries fetch
     *
     * @param countries [List] of countries
     */
    @WorkerThread
    private fun reportSuccessLoadingCountries(countries: List<Country>) {
        countryLiveData.postValue(Success(countries))
    }
}