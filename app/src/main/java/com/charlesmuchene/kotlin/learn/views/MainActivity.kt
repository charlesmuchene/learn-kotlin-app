package com.charlesmuchene.kotlin.learn.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.charlesmuchene.kotlin.learn.R
import com.charlesmuchene.kotlin.learn.data.Failure
import com.charlesmuchene.kotlin.learn.data.Success
import com.charlesmuchene.kotlin.learn.databinding.ActivityMainBinding
import com.charlesmuchene.kotlin.learn.models.Country
import com.charlesmuchene.kotlin.learn.utilities.*
import com.charlesmuchene.kotlin.learn.viewmodels.CountryViewModel
import com.charlesmuchene.kotlin.learn.views.recyclerview.CountryAdapter
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val viewModel: CountryViewModel by viewModels()
    private val countryAdapter: CountryAdapter by lazy { CountryAdapter(::countryClicked) }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupActivity()
        registerObservers()
        verifyInternetPermissionGranted()
    }

    private fun setupActivity() {
        binding.recyclerView.adapter = countryAdapter
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    /**
     * Method invoked when internet permission is granted
     */
    @AfterPermissionGranted(INTERNET_PERMISSION_REQUEST_CODE)
    private fun verifyInternetPermissionGranted() {
        if (EasyPermissions.hasPermissions(this, *permissions)) viewModel.fetchCountries()
        else EasyPermissions.requestPermissions(
            this,
            getString(R.string.permissions_rationale),
            INTERNET_PERMISSION_REQUEST_CODE,
            *permissions
        )
    }

    /**
     * Register countries Observers
     */
    private fun registerObservers() {

        viewModel.getCountryLiveData().observe(this, Observer { response ->
            @Suppress("UNCHECKED_CAST")
            when (response) {
                is Failure -> {
                    Timber.e(response.throwable)
                    showErrorLoadingCountries()
                }
                is Success<*> -> displayCountries(response.data as List<Country>)
            }
        })

    }

    /**
     * Display the given countries
     *
     * @param countries [List]
     */
    private fun displayCountries(countries: List<Country>) {
        countryAdapter.submitList(countries)
        binding.recyclerView.show()
        hideLoadingBar()
    }

    /**
     * Show error loading countries
     */
    private fun showErrorLoadingCountries() {
        binding.recyclerView.hide()
        hideLoadingBar()
        binding.errorView.show()
    }

    /**
     * Hide loading bar
     */
    private fun hideLoadingBar() {
        binding.loadingAnimationView.hide()
    }

    /**
     * Handle country click
     *
     * @param country [Country] instance
     */
    private fun countryClicked(country: Country) {
        val detailsIntent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(COUNTRY_PARCEL, country)
        }
        startActivity(detailsIntent)
    }

}
