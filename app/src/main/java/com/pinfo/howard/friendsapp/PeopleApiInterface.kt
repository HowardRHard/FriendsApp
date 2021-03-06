package com.pinfo.howard.friendsapp

import com.pinfo.howard.friendsapp.data.models.PeopleContainer
import retrofit2.Call
import retrofit2.http.GET

interface PeopleApiInterface {
    @GET("?seed=howard&results=50")
    fun getListOfRandoms(): Call<PeopleContainer>
}