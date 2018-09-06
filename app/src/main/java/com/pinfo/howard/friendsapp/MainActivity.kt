package com.pinfo.howard.friendsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.MenuItem
import com.pinfo.howard.friendsapp.dagger.Application
import com.pinfo.howard.friendsapp.people.PeopleFragment
import com.pinfo.howard.friendsapp.utils.addFragment
import com.pinfo.howard.friendsapp.utils.replaceFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, HasSupportFragmentInjector {

    @Inject lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private val bundle = Bundle()
    private var fragment = PeopleFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        //Inject the activity
        (application as Application).netComponent.inject(application as Application)
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setup the navigation drawer
        drawer_layout.addDrawerListener(ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close))
        drawerToggle = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = applicationContext.getString(R.string.home)
        navView.setNavigationItemSelectedListener(this)

        //Start the people fragment
        bundle.putString(applicationContext.getString(R.string.type), applicationContext.getString(R.string.home))
        fragment.arguments = bundle
        addFragment(fragment ,R.id.fragment_container,applicationContext.getString(R.string.home))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return drawerToggle.onOptionsItemSelected(item)
    }

    //Handles the navigation events
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        fragment = PeopleFragment()
        when (item.itemId) {
            R.id.home -> {
                bundle.putString(applicationContext.getString(R.string.type), applicationContext.getString(R.string.home))
                fragment.setArguments(bundle)
                supportActionBar?.title = applicationContext.getString(R.string.home)
                replaceFragment(fragment,R.id.fragment_container,applicationContext.getString(R.string.home))
            }
            R.id.friends -> {
                bundle.putString(applicationContext.getString(R.string.type), applicationContext.getString(R.string.friends))
                fragment.setArguments(bundle)
                supportActionBar?.title = applicationContext.getString(R.string.friends)
                replaceFragment(fragment,R.id.fragment_container,applicationContext.getString(R.string.friends))
            }
            else -> return false
        }

        drawer_layout.closeDrawer(Gravity.LEFT)
        return true
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mFragmentInjector
    }
}