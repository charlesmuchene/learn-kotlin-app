package com.charlesmuchene.kotlin.learn.business

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.charlesmuchene.kotlin.learn.data.Failure
import com.charlesmuchene.kotlin.learn.data.Success
import com.charlesmuchene.kotlin.learn.models.Country
import com.charlesmuchene.kotlin.learn.utilities.Configuration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Response

/**
 * Country view model
 */
class CountryViewModel : ViewModel() {

    val countryFailure = MutableLiveData<Failure>()
    val countrySuccess = MutableLiveData<Success<List<Country>>>()

    private val disposeBag = CompositeDisposable()

    /**
     * Fetch all countries from api
     */
    fun fetchAllCountries() {
        val subscribe = Configuration.apiService.getAllCountries()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleCountriesResponse)
        disposeBag.add(subscribe)
    }

    /**
     * Handle country fetch response
     *
     * @param response [Response] instance
     * @param throwable [Throwable] instance
     */
    private fun handleCountriesResponse(response: Response<List<Country>>?, throwable: Throwable?) {
        if (throwable != null) {
            reportErrorLoadingCountries(throwable)
        } else if (response != null) {
            response.body()?.let(::reportSuccessLoadingCountries)
                ?: reportErrorLoadingCountries(Throwable("Error loading countries"))
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

    /**
     * Clean up RX subscriptions
     */
    fun cleanUp() {
        disposeBag.dispose()
    }
}