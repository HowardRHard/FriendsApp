package com.pinfo.howard.friendsapp.people

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.pinfo.howard.friendsapp.PeopleListRecyclerViewAdapter
import com.pinfo.howard.friendsapp.R
import android.content.Context
import android.content.SharedPreferences
import com.pinfo.howard.friendsapp.PeopleApiInterface
import com.pinfo.howard.friendsapp.data.models.PeopleContainer
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.people_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject


class PeopleFragment : Fragment() {

    private lateinit var adapter: PeopleListRecyclerViewAdapter
    @Inject lateinit var retrofit: Retrofit
    @Inject lateinit var sharedPreferences: SharedPreferences
    private lateinit var type: String

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this )
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments?.get(context?.getString(R.string.type)).toString()
    }

    override fun onResume() {
        super.onResume()

        val apiService = retrofit.create(PeopleApiInterface::class.java)
        apiService.getListOfRandoms().enqueue(object : Callback<PeopleContainer> {

            override fun onResponse(call: Call<PeopleContainer>, response: Response<PeopleContainer>) {
                var peopleContainer = response.body()

                if (peopleContainer != null) {

                    //Removes all people that you aren't friends with
                    if(type == context?.getString(R.string.friends)) {
                        val friendList = peopleContainer.person.filter { s -> sharedPreferences.contains(s.login?.uuid)}
                        peopleContainer.person = friendList
                    }

                    // set up the RecyclerView
                    if (context != null && rv_friends != null) {
                        rv_friends.layoutManager = LinearLayoutManager(context)
                        adapter = PeopleListRecyclerViewAdapter(context!!, peopleContainer, type, sharedPreferences)
                        rv_friends.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<PeopleContainer>?, t: Throwable?) {
                Timber.e(t)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.people_fragment, container, false)
    }
}