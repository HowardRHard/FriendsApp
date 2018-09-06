package com.pinfo.howard.friendsapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FriendContainer {

    @SerializedName("results")
    @Expose
    lateinit var friend: List<Friend>

    @Expose
    lateinit var info: FriendInfo

}