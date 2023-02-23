package com.example.chitchat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(val context : Context,val userList :ArrayList<User>) : RecyclerView.Adapter<UserAdapter.userViewHolder>() {
    class userViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textName= itemView.findViewById<TextView>(R.id.txt_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
        val view : View  = LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
        return userViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {
        val currentUser=userList[position]
        holder.textName.text=currentUser.name
    }
}