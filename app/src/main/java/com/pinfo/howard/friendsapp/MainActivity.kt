package com.pinfo.howard.friendsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pinfo.howard.friendsapp.R.id.lImg
import com.pinfo.howard.friendsapp.R.id.rImg
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlideApp.with(applicationContext)
                    .load(applicationContext.getString(R.string.link_left))
                    .into(lImg)

            GlideApp.with(applicationContext)
                    .load(applicationContext.getString(R.string.link_right))
                    .into(rImg)
        }
}