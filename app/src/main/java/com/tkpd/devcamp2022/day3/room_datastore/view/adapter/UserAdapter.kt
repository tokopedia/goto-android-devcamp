package com.tkpd.devcamp2022.day3.room_datastore.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day3.room_datastore.model.UserData

class UserAdapter: RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var listUser = listOf<UserData>()

    @SuppressLint("NotifyDataSetChanged")
    fun setUsers(users: List<UserData>) {
        this.listUser = users
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: UserData) {
            val tvUserName = itemView.findViewById<TextView>(R.id.tv_user_name)
            val imgUser = itemView.findViewById<ImageView>(R.id.img_user)
            itemView.apply {
                tvUserName.text = user.lastName
                Glide.with(context)
                    .load(user.avatar)
                    .into(imgUser)
            }
        }
    }
}