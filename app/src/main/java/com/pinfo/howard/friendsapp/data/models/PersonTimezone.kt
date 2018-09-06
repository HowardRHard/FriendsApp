package com.pinfo.howard.friendsapp.data.models

import com.google.gson.annotations.Expose

class PersonTimezone {

    @Expose
    var offset: String? = null

    @Expose
    var description: String? = null

}