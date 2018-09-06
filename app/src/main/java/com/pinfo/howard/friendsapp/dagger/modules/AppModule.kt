package com.pinfo.howard.friendsapp.dagger.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides


@Module
class AppModule(private val application: Application) {
    @Provides
    fun provideApplication(): Context = application
}