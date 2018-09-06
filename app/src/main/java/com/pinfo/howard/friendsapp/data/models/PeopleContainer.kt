package com.pinfo.howard.friendsapp.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PeopleContainer {

    @SerializedName("results")
    @Expose
    lateinit var person: List<Person>

    @Expose
    lateinit var info: PersonInfo

}