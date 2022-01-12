package com.example.coronaviruslive.main.countrylive

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coronaviruslive.MainActivity
import com.example.coronaviruslive.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.coronaviruslive.countrylive.CountryLive
import java.util.*

class CovidCountryliveAdapter(private val CountriesCasesLive: ArrayList<CountryLive>, private val context: Context) : RecyclerView.Adapter<CovidCountryliveAdapter.ViewHolder>(), Filterable {
    var countryFilterList = ArrayList<CountryLive>()

    init {
        countryFilterList = CountriesCasesLive
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.covid19casesliste, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val covidCountry = countryFilterList[position]
        val main = context as MainActivity
        holder.itemView.setOnClickListener { main.onCountrySelected(covidCountry) }
        holder.tvTotalCases.text = Integer.toString(covidCountry.getmCases())
        holder.tvCountryName.text = covidCountry.getmCovidCountry()
        Glide.with(context)
            .load(covidCountry.getmFlags())
            .apply(RequestOptions().override(240, 160))
            .into(holder.imgCountryFlag)
    }

    override fun getItemCount(): Int {
        return countryFilterList.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTotalCases: TextView
        var tvCountryName: TextView
        var imgCountryFlag: ImageView

        init {
            tvTotalCases = itemView.findViewById(R.id.tvTotalCases)
            tvCountryName = itemView.findViewById(R.id.tvCountryName)
            imgCountryFlag = itemView.findViewById(R.id.imgCountryFlag)
        }
    }

    override fun getFilter(): Filter {
        return covidCountriesFilter
    }

    private val covidCountriesFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val charSearch = constraint.toString()
            if (charSearch == null || charSearch.length == 0) {
                countryFilterList = CountriesCasesLive
            } else {
                val resultList = ArrayList<CountryLive>()
                for (row in CountriesCasesLive) {
                    if (row.mCovidCountry!!.toLowerCase().contains(charSearch)) {
                        resultList.add(row)
                    }
                }
                countryFilterList = resultList
            }
            val filterResults = FilterResults()
            filterResults.values = countryFilterList
            return filterResults
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            countryFilterList = results?.values as ArrayList<CountryLive>
            notifyDataSetChanged()
        }

    }
}