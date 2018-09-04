package com.pinfo.howard.friendsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import com.pinfo.howard.friendsapp.models.FriendContainer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    lateinit var retrofit : Retrofit
    lateinit var gson : Gson
    lateinit var adapter : FriendListRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gson = GsonBuilder()
                .create()

        retrofit = Retrofit.Builder()
                .baseUrl(baseContext.getString(R.string.BASE_URL))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    override fun onResume() {
        super.onResume()
        val apiService = retrofit.create(FriendsApiInterface::class.java)

        val call = apiService.getListOfRandoms()
        call.enqueue(object : Callback<FriendContainer> {

            override fun onResponse(call: Call<FriendContainer>, response: Response<FriendContainer>) {
                val friendContainer = response.body()
                Timber.v(response.code().toString())

                if(friendContainer != null) {
                    //Set up the RecyclerView with people
                    rvFriends.layoutManager = LinearLayoutManager(baseContext)
                    adapter = FriendListRecyclerViewAdapter(baseContext, friendContainer)
                    rvFriends.adapter = adapter
                    rvFriends.invalidate()
                }
            }

            override fun onFailure(call: Call<FriendContainer>, t: Throwable) {
                Timber.e(t)
            }
        })
    }
}