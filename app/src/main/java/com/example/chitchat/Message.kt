package com.example.chitchat


// model of message
class Message {
    public var message: String?=null
    var senderId: String?=null // ? use for can be null and after that we marked it as null

    constructor(){}
    constructor(message: String?,senderId: String?){
        this.message=message
        this.senderId=senderId

    }



}