package com.example.shareandlend.model

import com.orm.SugarRecord


class User : SugarRecord<User>{

    var name: String? = null
    var password: String? = null
    var email: String? = null
    var dormNo: Int? = null

    constructor()

    constructor(name: String, password: String, email:String, dormNo: Int){
        this.name = name
        this.password = password
        this.email = email
        this.dormNo = dormNo
    }

}