package com.example.chitchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChatActivity : AppCompatActivity() {

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageBox : EditText
    private lateinit var sendButton : ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList:ArrayList<Message>
    private lateinit var mDbRef : DatabaseReference
//    private lateinit var textView: TextView

    var receiverRoom  : String ? =null
    var senderRoom : String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)


        // getting information from previous activity

        val name = intent.getStringExtra("name")
        val receiverUid = intent.getStringExtra("uid")
        val senderUid=FirebaseAuth.getInstance().currentUser?.uid

        mDbRef = FirebaseDatabase.getInstance().getReference()


        // creating unique room between sender and receiver so that nobody else can view this message .
        senderRoom = receiverUid + senderUid
        receiverRoom= senderUid + receiverUid

        // setting title of the activity
        supportActionBar?.title = name
//        supportActionBar?.hide()

//        textView = findViewById(R.id.txtview)
        chatRecyclerView = findViewById(R.id.chatRecyclerView)
        messageBox = findViewById(R.id.messageBox)
        sendButton = findViewById(R.id.sendButton)
        messageList = ArrayList()  // initilize messageList of type array list
        messageAdapter= MessageAdapter(this,messageList)

//        textView.text=name
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter= messageAdapter


        // logic for adding data  to recycler View
        mDbRef.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    messageList.clear()
                    for(postSnapshot in snapshot.children){
                        val message= postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        // adding the message to the database
        sendButton.setOnClickListener {

            val message = messageBox.text.toString()
            val messageObject = Message(message,senderUid)


            // creating a node of chat
            if(!message.isEmpty()){
                mDbRef.child("chats").child(senderRoom!!).child("messages").push().setValue(messageObject)
                    .addOnSuccessListener {
                        mDbRef.child("chats").child(receiverRoom!!).child("messages").push().setValue(messageObject)
                    }
                // clearing the text after pressing the send button
                messageBox.setText("")
            }


        }
    }

    // writing code for goint to profile section
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.profilemenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val name = intent.getStringExtra("name")
        if(item.itemId==R.id.profilesection){
            // write the code for goint to profile section
            val intent = Intent(this,ProfileSection::class.java)

            intent.putExtra("name",name)
            startActivity(intent)

            return true
        }
        return true;
    }

}