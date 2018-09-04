package com.pinfo.howard.friendsapp.models

import com.google.gson.annotations.Expose

class Friend {

    @Expose
    var gender: String? = null

    @Expose
    var name: FriendName? = null

    @Expose
    var location: FriendLocation? = null

    @Expose
    var email: String? = null

    @Expose
    var login: FriendLogin? = null

    @Expose
    var dob: FriendDob? = null

    @Expose
    var registered: FriendRegistered? = null

    @Expose
    var phone: String? = null

    @Expose
    var cell: String? = null

    @Expose
    var id: Id? = null

    @Expose
    var picture: FriendPicture? = null

    @Expose
    var nat: String? = null

}