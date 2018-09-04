package com.pinfo.howard.friendsapp.models

import com.google.gson.annotations.Expose

class FriendLocation {

    @Expose
    var street: String? = null

    @Expose
    var city: String? = null

    @Expose
    var state: String? = null

    @Expose
    var postcode: String? = null

    @Expose
    var coordinates: FriendCoordinates? = null

    @Expose
    var timezone: FriendTimezone? = null

}