package com.pinfo.howard.friendsapp.dagger

import com.pinfo.howard.friendsapp.dagger.modules.AppModule
import com.pinfo.howard.friendsapp.dagger.modules.FragmentModule
import com.pinfo.howard.friendsapp.dagger.modules.NetModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [(AppModule::class), (NetModule::class), (FragmentModule::class), (AndroidInjectionModule::class)])
interface NetComponent {
    fun inject(application: com.pinfo.howard.friendsapp.dagger.Application)
}