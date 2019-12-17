package com.example.shareandlend.model

import java.util.*

class Item {

    var itemName : String? = null
    var itemImagePath : String? = null
    var itemDescription: String? = null
    var availableFromDate : Date? = null
    var availableToDate : Date? = null
    var user: User? = null
    var fees: Double? = null
    var type: Int? = null

    constructor()

    constructor(itemName : String, itemImagePath : String, itemDescription: String, availableFromDate : Date, availableToDate : Date, user: User, fees: Double, type: Int){
        this.itemName = itemName
        this.itemImagePath = itemImagePath
        this.itemDescription = itemDescription
        this.availableFromDate = availableFromDate
        this.availableToDate =  availableToDate
        this.user = user
        this.fees = fees
        this.type = type
    }


}