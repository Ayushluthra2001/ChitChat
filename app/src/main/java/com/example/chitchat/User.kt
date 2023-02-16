package com.example.chitchat

class User  {

    // This class is used to store the details of the user .
    var name : String ? =null
    var email: String ? =null
    var uid: String ? =null

    //constructors
    constructor(){}
    constructor(name:String? , email:String? , uid:String?){
        this.name=name
        this.email=email
        this.uid=uid
    }
}