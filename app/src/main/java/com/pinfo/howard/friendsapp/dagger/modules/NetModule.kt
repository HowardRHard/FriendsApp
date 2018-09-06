package com.pinfo.howard.friendsapp.dagger.modules

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.Gson
import javax.inject.Singleton
import dagger.Provides
import com.google.gson.GsonBuilder
import android.content.Context
import android.preference.PreferenceManager
import android.content.SharedPreferences
import com.pinfo.howard.friendsapp.data.api.FriendApiInterface
import dagger.Module


@Module
class NetModule(private val baseUrl: String) {

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
    // Application reference must come from AppModule.class
        return PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        var gson = GsonBuilder()
                .create()
        return gson
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .build()
    }

    @Provides
    @Singleton
    fun provideFriendApiInterface(retrofit: Retrofit): FriendApiInterface{
        return retrofit.create(FriendApiInterface::class.java)
    }
}