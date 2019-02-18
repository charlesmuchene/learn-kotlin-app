package com.charlesmuchene.kotlin.learn.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.charlesmuchene.kotlin.learn.R
import com.charlesmuchene.kotlin.learn.business.CountryViewModel
import com.charlesmuchene.kotlin.learn.models.Country
import com.charlesmuchene.kotlin.learn.utilities.INTERNET_PERMISSION_REQUEST_CODE
import com.charlesmuchene.kotlin.learn.utilities.permissions
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        internetPermissionGranted()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cleanUp()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    /**
     * Method invoked when internet permission is granted
     */
    @AfterPermissionGranted(INTERNET_PERMISSION_REQUEST_CODE)
    private fun internetPermissionGranted() {
        if (EasyPermissions.hasPermissions(this, *permissions)) fetchCountries()
        else EasyPermissions.requestPermissions(
            this,
            getString(R.string.permissions_rationale),
            INTERNET_PERMISSION_REQUEST_CODE,
            *permissions
        )
    }

    /**
     * Fetch countries
     */
    private fun fetchCountries() {

        viewModel.fetchAllCountries()

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
        // Process elements
        for (country in countries) {
            Timber.e(country.capital)
        }
        recyclerView.show()
        hideLoadingBar()
    }

    /**
     * Show error loading countries
     */
    private fun showErrorLoadingCountries() {
        hideLoadingBar()
        waitAnimationView.show()
    }

    /**
     * Hide loading bar
     */
    private fun hideLoadingBar() {
        loadingAnimationView.hide()
    }

}
