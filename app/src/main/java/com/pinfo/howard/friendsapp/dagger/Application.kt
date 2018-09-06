package com.pinfo.howard.friendsapp.dagger

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.pinfo.howard.friendsapp.R
import com.pinfo.howard.friendsapp.dagger.modules.AppModule
import com.pinfo.howard.friendsapp.dagger.modules.NetModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class Application : Application(), HasActivityInjector, HasSupportFragmentInjector {

    lateinit var netComponent: NetComponent


    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()

        netComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(NetModule(baseContext.getString(R.string.BASE_URL)))
                .build()

        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        //  mNetComponent = com.codepath.dagger.components.DaggerNetComponent.create();*/
    }


    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}