package com.example.chitchat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context : Context , val messageList : ArrayList<Message>) : RecyclerView.Adapter<ViewHolder>() {


    // Two View Holder one for receiving the message and one for sending the message

    val Item_receive = 1
    val Item_sent =2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        if(viewType==1){
            // inflate receive
            val view : View  = LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            return ReceiveViewHolder(view)
        }else{
            // inflate sent
            val view : View  = LayoutInflater.from(context).inflate(R.layout.send,parent,false)
            return SentViewHolder(view)
        }


    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currMessage = messageList[position] // getting current message from message list from current position
        if(holder.javaClass == SentViewHolder::class.java){
            // write the code for sent view holder
            val viewHolder = holder as SentViewHolder // type casting as sentViewHolder


             holder.sentMessage.text =currMessage.message


        }else{
            // write the code for Receive view holder
            val viewHolder = holder as ReceiveViewHolder

            holder.receiveMessage.text =currMessage.message
        }


    }

    override fun getItemViewType(position: Int): Int {
        val  currentMessage = messageList[position]

        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            // if we are the current user then view holder we send is sent
            return Item_sent;
        }else{
            return Item_receive
        }


    }

    // sending view holder
    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val sentMessage = itemView.findViewById<TextView>(R.id.txt_sent_message)
    }

    // Receiving View Holder
    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val receiveMessage = itemView.findViewById<TextView>(R.id.txt_receive_message)
    }
}