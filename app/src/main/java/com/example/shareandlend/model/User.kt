package com.example.shareandlend.model

import java.io.Serializable


class User : Serializable{

    var name: String? = null
    var password: String? = null
    var email: String? = null
    var dormNo: Int? = null
    var phoneNo: Int? = null

    constructor()

    constructor(name: String, password: String, email:String, dormNo: Int, phoneNo: Int){
        this.name = name
        this.password = password
        this.email = email
        this.dormNo = dormNo
        this.phoneNo = phoneNo
    }

}