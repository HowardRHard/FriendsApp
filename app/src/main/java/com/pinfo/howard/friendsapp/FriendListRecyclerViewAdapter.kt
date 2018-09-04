package com.pinfo.howard.friendsapp

import android.content.Context
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.pinfo.howard.friendsapp.models.Friend
import com.pinfo.howard.friendsapp.models.FriendContainer
import kotlinx.android.synthetic.main.friend_item.view.*

class FriendListRecyclerViewAdapter
internal constructor(context: Context, private val friendContainer: FriendContainer) : RecyclerView.Adapter<FriendListRecyclerViewAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private lateinit var mClickListener: ItemClickListener

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.friend_item, parent, false)
        return ViewHolder(view)
    }

    //Sets the text and images for each person as it is called with their position in the FriendContainer object
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = getFriend(position)
        val fullName = "${friend?.name?.first?.capitalize()} ${friend?.name?.last?.capitalize()}"

        holder.friendName.text = fullName
        holder.friendAge.text = friend?.dob?.age
        holder.friendCity.text = friend?.location?.city?.capitalize()

        Glide.with(holder.itemView)
                .load(friendContainer.friend[position].picture?.large)
                .into(holder.itemView.findViewById(R.id.friendPhoto))
    }

    //Stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        internal var friendName: TextView = itemView.friendName
        internal var friendAge: TextView = itemView.friendAge
        internal var friendCity: TextView = itemView.friendCity

        override fun onClick(v: View?) {
            mClickListener.onItemClick(itemView, adapterPosition)
        }
    }

    private fun getFriend(id: Int): Friend? {
        return friendContainer.friend[id]
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    override fun getItemCount(): Int {
        return friendContainer.friend.size
    }
}