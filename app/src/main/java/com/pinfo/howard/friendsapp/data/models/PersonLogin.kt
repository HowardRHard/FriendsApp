package com.pinfo.howard.friendsapp.data.models


import com.google.gson.annotations.Expose

class PersonLogin {

    @Expose
    var uuid: String? = null

    @Expose
    var username: String? = null

    @Expose
    var password: String? = null

    @Expose
    var salt: String? = null

    @Expose
    var md5: String? = null

    @Expose
    var sha1: String? = null

    @Expose
    var sha256: String? = null

}