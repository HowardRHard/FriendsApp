package com.pinfo.howard.friendsapp.data.models

import com.google.gson.annotations.Expose

class PersonInfo {

    @Expose
    var seed: String? = null

    @Expose
    var results: Int = 0

    @Expose
    var page: Int = 0

    @Expose
    var version: String? = null

}