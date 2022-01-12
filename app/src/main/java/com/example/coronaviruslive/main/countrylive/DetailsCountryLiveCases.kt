package com.example.coronaviruslive.main.countrylive

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.coronaviruslive.R
import com.example.coronaviruslive.countrylive.CountryLive

class DetailsCountryLiveCases : AppCompatActivity() {
    var tvDetailCountryName: TextView? = null
    var tvDetailTotalCases: TextView? = null
    var tvDetailTodayCases: TextView? = null
    var tvDetailTotalDeaths: TextView? = null
    var tvDetailTodayDeaths: TextView? = null
    var tvDetailTotalRecovered: TextView? = null
    var tvDetailTotalActive: TextView? = null
    var tvDetailTotalCritical: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_country_live_cases)

        tvDetailCountryName = findViewById(R.id.tvDetailCountryName)
        tvDetailTotalCases = findViewById(R.id.tvDetailTotalCases)
        tvDetailTodayCases = findViewById(R.id.tvDetailTodayCases)
        tvDetailTotalDeaths = findViewById(R.id.tvDetailTotalDeaths)
        tvDetailTodayDeaths = findViewById(R.id.tvDetailTodayDeaths)
        tvDetailTotalRecovered = findViewById(R.id.tvDetailTotalRecovered)
        tvDetailTotalActive = findViewById(R.id.tvDetailTotalActive)
        tvDetailTotalCritical = findViewById(R.id.tvDetailTotalCritical)

        val countryLive: CountryLive = intent.getParcelableExtra("EXTRA_COVID")

        tvDetailCountryName!!.setText(countryLive.getmCovidCountry())
        tvDetailTotalCases!!.setText(Integer.toString(countryLive.getmCases()))
        tvDetailTodayCases!!.setText(countryLive.getmTodayCases())
        tvDetailTotalDeaths!!.setText(countryLive.getmDeaths())
        tvDetailTodayDeaths!!.setText(countryLive.getmTodayDeaths())
        tvDetailTotalRecovered!!.setText(countryLive.getmRecovered())
        tvDetailTotalActive!!.setText(countryLive.getmActive())
        tvDetailTotalCritical!!.setText(countryLive.getmCritical())
    }
}

