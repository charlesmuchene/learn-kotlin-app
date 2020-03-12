package com.charlesmuchene.kotlin.learn.views

import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.charlesmuchene.kotlin.learn.R
import com.charlesmuchene.kotlin.learn.databinding.ActivityDetailsBinding
import com.charlesmuchene.kotlin.learn.models.Country
import com.charlesmuchene.kotlin.learn.utilities.COUNTRY_PARCEL
import com.charlesmuchene.kotlin.learn.utilities.show
import com.charlesmuchene.kotlin.learn.utilities.svg.SvgSoftwareLayerSetter
import com.charlesmuchene.kotlin.learn.viewmodels.CountryDetailsViewModel
import kotlinx.android.synthetic.main.activity_details.*

/**
 * Country details activity
 */
class DetailsActivity : AppCompatActivity() {

    private val detailsViewModel: CountryDetailsViewModel by viewModels()

    private val requestBuilder: RequestBuilder<PictureDrawable>
        get() = Glide.with(this)
            .`as`(PictureDrawable::class.java)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .listener(SvgSoftwareLayerSetter())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewmodel = detailsViewModel
        binding.lifecycleOwner = this
        val country = intent.getParcelableExtra<Parcelable>(COUNTRY_PARCEL) as? Country
        country?.let { displayCountry(binding, country) } ?: run { errorView.show() }
        country?.name?.let(::setTitle)
    }

    /**
     * Display the given country details
     *
     * @param binding [ActivityDetailsBinding] instance
     * @param country [Country] instance
     */
    private fun displayCountry(binding: ActivityDetailsBinding, country: Country) {
        binding.country = country
        requestBuilder.load(country.flag).into(binding.flagImageView)
    }
}