package com.pinfo.howard.friendsapp.data.models

import com.google.gson.annotations.Expose

class Person {

    @Expose
    var gender: String? = null

    @Expose
    var name: PersonName? = null

    @Expose
    var location: PersonLocation? = null

    @Expose
    var email: String? = null

    @Expose
    var login: PersonLogin? = null

    @Expose
    var dob: PersonDob? = null

    @Expose
    var registered: PersonRegistered? = null

    @Expose
    var phone: String? = null

    @Expose
    var cell: String? = null

    @Expose
    var id: Id? = null

    @Expose
    var picture: PersonPicture? = null

    @Expose
    var nat: String? = null

}