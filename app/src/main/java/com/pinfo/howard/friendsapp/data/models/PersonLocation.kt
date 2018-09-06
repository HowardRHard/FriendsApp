package com.pinfo.howard.friendsapp.data.models

import com.google.gson.annotations.Expose

class PersonLocation {

    @Expose
    var street: String? = null

    @Expose
    var city: String? = null

    @Expose
    var state: String? = null

    @Expose
    var postcode: String? = null

    @Expose
    var coordinates: PersonCoordinates? = null

    @Expose
    var timezone: PersonTimezone? = null

}