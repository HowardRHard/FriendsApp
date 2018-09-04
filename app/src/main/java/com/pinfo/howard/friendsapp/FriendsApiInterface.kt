package com.pinfo.howard.friendsapp

import com.pinfo.howard.friendsapp.models.FriendContainer
import retrofit2.Call
import retrofit2.http.GET

interface FriendsApiInterface {
    @GET("?seed=howard&results=50")
    fun getListOfRandoms(): Call<FriendContainer>
}