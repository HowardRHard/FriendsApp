package com.pinfo.howard.friendsapp.dagger.modules

import com.pinfo.howard.friendsapp.MainActivity
import com.pinfo.howard.friendsapp.people.PeopleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeHomFragment(): PeopleFragment

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}