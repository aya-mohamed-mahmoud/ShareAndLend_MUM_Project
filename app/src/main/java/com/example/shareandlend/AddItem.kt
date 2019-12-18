package com.example.shareandlend

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shareandlend.model.Item
import com.example.shareandlend.model.ShareType
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_item.*
import kotlinx.android.synthetic.main.create_new_account.*
import java.text.SimpleDateFormat
import java.util.*


class AddItem : AppCompatActivity() {

    lateinit var firestore: FirebaseFirestore
    val ITEMS_COLLECTION: String = "Items"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        firestore = FirebaseFirestore.getInstance()


    }


    private fun validateInsertedData(): Boolean {

        var inValid = false
        if (item_name.text == null || !item_name.text.isNotBlank()) {
            inValid = true
            item_name.error = "Please insert item name"
        }
        if (fees.text == null || !fees.text.isNotBlank()) {
            inValid = true
            fees.error = "Please insert fees"
        }
        return inValid
    }

    fun addItem(view: View) {
        println("addItem")
        if (!validateInsertedData()) {
            var format = SimpleDateFormat("dd-MM-yyyy")
            var fromDate: String? = ""
            var toDate: String? = ""

            val datePickerFrom = findViewById<DatePicker>(R.id.from)
            val today = Calendar.getInstance()

            datePickerFrom.init(today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH),

                DatePicker.OnDateChangedListener { view, year, month, day ->
                    val month = month + 1
                    fromDate = "$day/$month/$year"
                }
            )


            val datePickerTo = findViewById<DatePicker>(R.id.to)
            datePickerTo.init(today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH),

                DatePicker.OnDateChangedListener { view, year, month, day ->
                    val month = month + 1
                    toDate = "$day/$month/$year"
                    Toast.makeText(
                        getApplicationContext(),
                        "onDateChanged", Toast.LENGTH_SHORT
                    ).show();
                }
            )


            val item = Item()
            item.itemName = item_name.text.toString()
            item.availableFromDate = format.parse(fromDate.toString())
            item.availableToDate = format.parse(toDate.toString())
            item.fees = fees.text.toString().toDouble()
            item.itemDescription = item_desc.text.toString()
            item.itemImagePath = "drwable"
            item.type = ShareType.SHARE.value

            firestore.collection(ITEMS_COLLECTION).document(item.itemName.toString()).get()
                .addOnSuccessListener { documentSnapshot ->
                    firestore.collection(ITEMS_COLLECTION).document(item.itemName.toString())
                        .set(item).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    "Item Added Successfully ! ",
                                    Toast.LENGTH_SHORT
                                ).show()
                                goToMainActivity()
                            }
                        }
                }
        }
    }


    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
