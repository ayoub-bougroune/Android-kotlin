package com.example.coronaviruslive

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.coronaviruslive.main.countrylive.CountryliveFragment
import com.example.coronaviruslive.countrylive.CountryLive
import com.example.coronaviruslive.main.countrylive.DetailsCountryLiveCases
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), CountryliveFragment.OnCountrySelected {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        navView.itemIconTintList = null

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(navView, navController)
    }

    override fun onCountrySelected(countryLive: CountryLive) {
        val detailsCountryLiveCases = Intent(this@MainActivity, DetailsCountryLiveCases::class.java)
        detailsCountryLiveCases.putExtra("EXTRA_COVID", countryLive)
        startActivity(detailsCountryLiveCases)
    }
}
