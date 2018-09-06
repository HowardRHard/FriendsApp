package com.pinfo.howard.friendsapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.pinfo.howard.friendsapp.data.models.Person
import com.pinfo.howard.friendsapp.data.models.PeopleContainer

class PeopleListRecyclerViewAdapter
internal constructor(val context: Context, private val peopleContainer: PeopleContainer,
                     private val type: String, private val sharedPreferences: SharedPreferences) : RecyclerView.Adapter<PeopleListRecyclerViewAdapter.ViewHolder>() {

    private val mInflater: LayoutInflater

    init {
        this.mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.friend_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = getFriend(position)

        //Set the text information for the person
        holder.personName.text = friend?.name?.first?.capitalize() + " " + friend?.name?.last?.capitalize()
        holder.personAge.text = friend?.dob?.age
        holder.personCity.text = friend?.location?.city?.capitalize()

        //If the type is home, display the add button
        if(type == context.getString(R.string.home)) {
            var isFriend = sharedPreferences.contains(friend?.login?.uuid)
            var color = sharedPreferences.getInt(friend?.login?.uuid, ContextCompat.getColor(context, R.color.colorBlack))

            holder.friendControls.visibility = View.GONE
            holder.homeControls.visibility = View.VISIBLE

            holder.addFriend.setOnClickListener {
                //if the person has already been added, remove them and change the color of the add image
                if(isFriend) {
                    color = ContextCompat.getColor(context, R.color.colorBlack)
                    sharedPreferences.edit().remove(friend?.login?.uuid).apply()
                    isFriend = sharedPreferences.contains(friend?.login?.uuid)
                }
                //If they haven't then do the opposite
                else {
                    color = ContextCompat.getColor(context, R.color.colorGreen)
                    sharedPreferences.edit().putInt(friend?.login?.uuid, color).apply()
                    isFriend = sharedPreferences.contains(friend?.login?.uuid)

                }
                holder.addFriend.setColorFilter(color)
            }
            holder.addFriend.setColorFilter(color)

        } //Otherwise show the email and call buttons
        else {
            holder.friendControls.visibility = View.VISIBLE
            holder.homeControls.visibility = View.GONE

            //Enables user to call their person
            holder.callFriend.setOnClickListener {
                var callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                callIntent.data = Uri.parse("tel:${friend?.cell}")
                context.applicationContext.startActivity(callIntent)
            }

            //Enables user to email their person
            holder.emailFriend.setOnClickListener {
                var emailIntent = Intent(Intent.ACTION_SENDTO)
                emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                emailIntent.data = Uri.parse("mailto: ${friend?.email}")
                context.startActivity(emailIntent)
            }

        }

        //Set the persons picture
        Glide.with(holder.itemView)
                .load(peopleContainer.person[position].picture?.large)
                .into(holder.itemView.findViewById(R.id.friendPhoto))
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        internal var personName: TextView
        internal var personAge: TextView
        internal var personCity: TextView
        internal var friendControls: LinearLayout
        internal var homeControls: LinearLayout
        internal var emailFriend: ImageView
        internal var callFriend: ImageView
        internal var addFriend: ImageView

        init {
            personName = itemView.findViewById(R.id.person_name)
            personAge = itemView.findViewById(R.id.person_age)
            personCity = itemView.findViewById(R.id.person_city)

            friendControls = itemView.findViewById(R.id.controls_friend)
            emailFriend = itemView.findViewById(R.id.email_friend)
            callFriend = itemView.findViewById(R.id.call_friend)

            homeControls = itemView.findViewById(R.id.controls_home)
            addFriend = itemView.findViewById(R.id.add_friend)
        }

        override fun onClick(v: View?) {

        }
    }

    private fun getFriend(id: Int): Person? {
        return peopleContainer.person.get(id)
    }

    override fun getItemCount(): Int {
        return peopleContainer.person.size ?: 0
    }
}