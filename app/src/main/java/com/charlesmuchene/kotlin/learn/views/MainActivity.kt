package com.charlesmuchene.kotlin.learn.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.charlesmuchene.kotlin.learn.R
import com.charlesmuchene.kotlin.learn.databinding.ActivityMainBinding
import com.charlesmuchene.kotlin.learn.models.Country
import com.charlesmuchene.kotlin.learn.utilities.*
import com.charlesmuchene.kotlin.learn.viewmodels.CountryViewModel
import com.charlesmuchene.kotlin.learn.views.recyclerview.CountryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val viewModel: CountryViewModel by viewModels()
    private val countryAdapter: CountryAdapter by lazy { CountryAdapter(::countryClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActivity(binding.recyclerView)
        registerObservers()
        verifyInternetPermissionGranted()
    }

    private fun setupActivity(recyclerView: RecyclerView) {
        recyclerView.adapter = countryAdapter
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

        viewModel.countryFailure.observe(this, Observer { failure ->
            Timber.e(failure.throwable)
            showErrorLoadingCountries()
        })

        viewModel.countrySuccess.observe(this, Observer { success ->
            displayCountries(success.data)
        })

    }

    /**
     * Display the given countries
     *
     * @param countries [List]
     */
    private fun displayCountries(countries: List<Country>) {
        countryAdapter.submitList(countries)
        recyclerView.show()
        hideLoadingBar()
    }

    /**
     * Show error loading countries
     */
    private fun showErrorLoadingCountries() {
        recyclerView.hide()
        hideLoadingBar()
        errorView.show()
    }

    /**
     * Hide loading bar
     */
    private fun hideLoadingBar() {
        loadingAnimationView.hide()
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
