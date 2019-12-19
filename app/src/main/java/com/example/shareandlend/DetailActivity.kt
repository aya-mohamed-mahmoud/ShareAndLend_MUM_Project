package com.example.shareandlend

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shareandlend.model.Item
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.add_item.*
import kotlinx.android.synthetic.main.item_details.*
import kotlinx.android.synthetic.main.item_details.fees
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    lateinit var item: Item
    lateinit var firestore: FirebaseFirestore
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_details)
        // To avoid application crash check intent has data or not
        val intent = getIntent()

        item = intent.getSerializableExtra("item") as Item

        if (item != null) {
            desc.text = item.itemDescription.toString()
            name.text = item.itemName.toString()
            available_date_from.text = SimpleDateFormat("MM/dd/yyyy").format(item.availableFromDate)
            available_date_to.text = SimpleDateFormat("MM/dd/yyyy").format(item.availableToDate)
            fees.text = item.fees.toString()
            contact_me.text = item.user!!.phoneNo.toString()
        }
    }

    fun bookItem(view: View) {

        Toast.makeText(this, "item booked", Toast.LENGTH_SHORT).show()
        item.available = false
        databaseReference = FirebaseDatabase.getInstance().reference
        databaseReference.child("Items").child(item.itemId.toString()).setValue(item)
       // goToMainActivity()

    }
    fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}