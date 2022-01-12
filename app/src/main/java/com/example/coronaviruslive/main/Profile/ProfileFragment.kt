package com.example.coronaviruslive.main.Profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.coronaviruslive.R
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class ProfileFragment : Fragment() {
    private var tvTotalConfirmed: TextView? = null
    private var tvTotalDeaths: TextView? = null
    private var tvTotalRecovered: TextView? = null
    private var tvLastUpdated: TextView? = null
    private var progressBar: ProgressBar? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        tvTotalConfirmed = root.findViewById(R.id.tvTotalConfirmed)
        tvTotalDeaths = root.findViewById(R.id.tvTotalDeaths)
        tvTotalRecovered = root.findViewById(R.id.tvTotalRecovered)
        tvLastUpdated = root.findViewById(R.id.tvLastUpdated)
        progressBar = root.findViewById(R.id.progress_circular_home)

        activity!!.title = ""

        data
        return root
    }

    private fun getDate(milliSecond: Long): String {
        val formatter = SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss aaa")
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSecond
        return formatter.format(calendar.time)
    }

    private val data: Unit
        private get() {
            val queue = Volley.newRequestQueue(activity)
            val url = "https://corona.lmao.ninja/v2/all"
            val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener { response ->
                progressBar!!.visibility = View.GONE
                try {
                    val jsonObject = JSONObject(response)
                    tvTotalConfirmed!!.text = jsonObject.getString("cases")
                    tvTotalDeaths!!.text = jsonObject.getString("deaths")
                    tvTotalRecovered!!.text = jsonObject.getString("recovered")
                    tvLastUpdated!!.text = "Last Updated" + "\n" + getDate(jsonObject.getLong("updated"))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error ->
                progressBar!!.visibility = View.GONE
                Log.d("Error Response", error.toString())
            })
            queue.add(stringRequest)
        }
}