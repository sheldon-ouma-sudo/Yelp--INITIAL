package com.sheldon.myyelpcloneapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
private const val TAG = "MainActivity"
private const val BASE_URL = "https://api.yelp.com/v3/"
private const val API_KEY = "8BkyZGU74_RAEIIGEn6czEwOhO3O_GJAc3xekwBRWP8EeUQ_LNMiph7jCOBgRZBNRqUw66VHe908BVxBuag5XO0uzeB4jYX_CVTRsMjKt41dRXeNfiIMAYB5p2nJXnYx"
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resturants = mutableListOf<YelpResturant>()
        val adapter = ResturantsAdapter(this,resturants)
        rvResturants.adapter = adapter
        rvResturants.layoutManager= LinearLayoutManager(this)



        val retrofit=
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
    val yelpService =retrofit.create(YelpService::class.java)
        Log.i(TAG,"About to make API")
    yelpService.searchResturants("Bearer $API_KEY", "Pizza", "Palo Alto").enqueue(object : Callback< YelpSearchResult>{

        override fun onResponse(call: Call <YelpSearchResult>, response: Response< YelpSearchResult>) {
            Log.i(TAG,"onResponse $response")
            val body = response.body()
            if(body ==null){
                Log.w(TAG, "Invalid operation...exiting")
             return
            }
            Log.i(TAG,"The number of responses${body.resturants.size}")
         resturants.addAll(body.resturants)
            adapter.notifyDataSetChanged()
        }


        override fun onFailure(call: Call< YelpSearchResult>, t: Throwable) {

            Log.i(TAG,"onFailure $t")
        }


    })
    }
}
