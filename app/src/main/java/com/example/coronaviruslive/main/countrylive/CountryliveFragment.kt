package com.example.coronaviruslive.main.countrylive

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coronaviruslive.MainActivity
import com.example.coronaviruslive.R
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.coronaviruslive.countrylive.CountryLive
import org.json.JSONArray
import org.json.JSONException
import java.util.*


class CountryliveFragment : Fragment() {
    var rvCovidCountry: RecyclerView? = null
    var progressBar: ProgressBar? = null
    var covidCountryliveAdapter: CovidCountryliveAdapter? = null
    var CountriesLive: ArrayList<CountryLive>? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_countrylive, container, false)
        setHasOptionsMenu(true)
        rvCovidCountry = root.findViewById(R.id.rvCovidCountry)
        progressBar = root.findViewById(R.id.progress_circular_country)
        rvCovidCountry!!.setLayoutManager(LinearLayoutManager(activity))
        val dividerItemDecoration = DividerItemDecoration(rvCovidCountry!!.getContext(), DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.line_divider)!!)
        rvCovidCountry!!.addItemDecoration(dividerItemDecoration)

        CountriesLive = ArrayList()

        dataFromServerSortTotalCases

        return root
    }

    private fun showRecyclerView() {
        covidCountryliveAdapter = CovidCountryliveAdapter(CountriesLive!!, activity!!)
        rvCovidCountry!!.adapter = covidCountryliveAdapter

    }



    private val dataFromServerSortTotalCases:
            Unit
        private get() {
            val url = "https://corona.lmao.ninja/v2/countries"
            val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener { response ->
                progressBar!!.visibility = View.GONE
                if (response != null) {
                    Log.e(TAG, "onResponse: $response")
                    try {
                        val jsonArray = JSONArray(response)
                        for (i in 0 until jsonArray.length()) {
                            val data = jsonArray.getJSONObject(i)
                            // Extract JSONObject inside JSONObject
                            val countryInfo = data.getJSONObject("countryInfo")
                            CountriesLive!!.add(CountryLive(
                                data.getString("country"), data.getInt("cases"),
                                data.getString("todayCases"), data.getString("deaths"),
                                data.getString("todayDeaths"), data.getString("recovered"),
                                data.getString("active"), data.getString("critical"),
                                countryInfo.getString("flag")
                            ))
                        }
                        // sort descending
                        Collections.sort(CountriesLive) { o1, o2 ->
                            if (o1.getmCases() > o2.getmCases()) {
                                -1
                            } else {
                                1
                            }
                        }
                        // Action Bar Title
                        activity!!.title = jsonArray.length().toString() + " countries"
                        showRecyclerView()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            },
                Response.ErrorListener { error ->
                    progressBar!!.visibility = View.GONE
                    Log.e(TAG, "onResponse: $error")
                })
            Volley.newRequestQueue(activity).add(stringRequest)
        }

    companion object {
        private val TAG = CountryliveFragment ::class.java.simpleName
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.bar_search)
        val searchView =
            SearchView(activity)
        searchView.queryHint = "Search..."
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (covidCountryliveAdapter != null) {
                    covidCountryliveAdapter!!.getFilter().filter(newText)
                }
                return true
            }
        })
        searchItem.actionView = searchView
        super.onCreateOptionsMenu(menu, inflater)
    }



    interface OnCountrySelected {


        fun onCountrySelected(countryLive: CountryLive)
    }
}