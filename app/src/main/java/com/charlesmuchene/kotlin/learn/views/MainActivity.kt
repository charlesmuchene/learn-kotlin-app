package com.charlesmuchene.kotlin.learn.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.charlesmuchene.kotlin.learn.R
import com.charlesmuchene.kotlin.learn.utilities.Configuration
import com.charlesmuchene.kotlin.learn.utilities.INTERNET_PERMISSION_REQUEST_CODE
import com.charlesmuchene.kotlin.learn.utilities.permissions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val disposeBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        internetPermissionGranted()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.dispose()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

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

    private fun fetchCountries() {
        val subscribe = Configuration.apiService.getAllCountries()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                if (response.isSuccessful)
                    response.body()?.forEach { Timber.e(it.name) }
                else Timber.e("Nothing came through")
            }

        disposeBag.add(subscribe)
    }
}
