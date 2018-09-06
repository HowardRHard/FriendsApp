package com.pinfo.howard.friendsapp.data.api

import com.pinfo.howard.friendsapp.data.models.PeopleContainer
import retrofit2.Call
import retrofit2.http.GET

interface FriendApiInterface {
    // Request method and URL specified in the annotation

    @GET("?seed=howard&results=50")
    fun getListOfRandoms(): Call<PeopleContainer>
}