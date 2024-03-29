package com.example.shareandlend.model

import java.io.Serializable
import java.util.*

class Item : Serializable {
    var itemId:String? = null
    var itemName: String? = null
    var itemDescription: String? = null
    var availableFromDate: Date? = null
    var availableToDate: Date? = null
    var user: User? = null
    var fees: Double? = null
    var type: Int? = null
    var available: Boolean? = null

    constructor()
    constructor(itemName: String, itemDescription: String, availableFromDate: Date, availableToDate: Date, user: User, fees: Double, type: Int, available: Boolean) {
        this.itemName = itemName
        this.itemDescription = itemDescription
        this.availableFromDate = availableFromDate
        this.availableToDate = availableToDate
        this.user = user
        this.fees = fees
        this.type = type
        this.available = available
    }
}