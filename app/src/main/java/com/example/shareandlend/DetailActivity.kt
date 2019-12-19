package com.example.shareandlend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shareandlend.model.Item
import kotlinx.android.synthetic.main.add_item.*
import kotlinx.android.synthetic.main.item_details.*
import kotlinx.android.synthetic.main.item_details.fees
import java.text.SimpleDateFormat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_details)
        // To avoid application crash check intent has data or not
        val intent = getIntent()

        val item = intent.getSerializableExtra("item") as Item

        if(item != null) {
            desc.text = item.itemDescription.toString()
            name.text = item.itemName.toString()
            available_date_from.text = SimpleDateFormat("MM/dd/yyyy").format(item.availableFromDate)
            available_date_to.text = SimpleDateFormat("MM/dd/yyyy").format(item.availableToDate)
            fees.text = item.fees.toString()
            contact_me.text = item.user!!.phoneNo.toString()
        }
    }
}