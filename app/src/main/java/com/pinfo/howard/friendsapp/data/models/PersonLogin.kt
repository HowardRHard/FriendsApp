package com.pinfo.howard.friendsapp.models


import com.google.gson.annotations.Expose

class FriendLogin {

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