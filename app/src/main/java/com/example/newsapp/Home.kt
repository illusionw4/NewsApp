package com.example.newsapp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest


class Home : AppCompatActivity(), Newsitemclicked {

    private lateinit var mrecycler: RecyclerView
    private lateinit var myadapter: Newsadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        mrecycler = findViewById(R.id.recyclerView)
        mrecycler.layoutManager = LinearLayoutManager(this)
        myadapter = Newsadapter( this)
        mrecycler.adapter = myadapter

    load()
    }


    fun load(){
        val url = "https://saurav.tech/NewsAPI/everything/cnn.json"
// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener
            {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<Newsmodel>()
                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = Newsmodel(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )
                    newsArray.add(news)
                }
                myadapter.upnews(newsArray)
            },
            Response.ErrorListener {
                Log.i("newsJsonArray", url)
                Log.ERROR
                Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()

            }
        )
// Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: Newsmodel) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
     }


}