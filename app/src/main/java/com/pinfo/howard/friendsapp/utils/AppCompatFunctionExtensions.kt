package com.pinfo.howard.friendsapp.utils

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, backStackTag: String? = null) {
    supportFragmentManager.beginTransaction()
            .add(frameId, fragment, backStackTag)
            .addToBackStack(backStackTag)
            .commit()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, backStackTag: String? = null) {
    supportFragmentManager.beginTransaction()
            .replace(frameId, fragment, backStackTag)
            .addToBackStack(backStackTag)
            .commit()
}