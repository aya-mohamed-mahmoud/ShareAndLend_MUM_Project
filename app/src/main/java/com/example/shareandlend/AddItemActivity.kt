package com.example.shareandlend

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shareandlend.model.Item
import com.example.shareandlend.model.ShareType
import com.example.shareandlend.model.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.add_item.*
import java.text.SimpleDateFormat
import java.util.*


class AddItemActivity : AppCompatActivity() {

    lateinit var firestore: FirebaseFirestore
    val ITEMS_COLLECTION: String = "Items"
    lateinit var from: EditText
    lateinit var to: EditText
    var loggedInUser: User? = User()

    lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_item)

        //get user from firebase
        loggedInUser = intent.getSerializableExtra("user") as User

        databaseReference = FirebaseDatabase.getInstance().reference

        firestore = FirebaseFirestore.getInstance()
        from = findViewById(R.id.from)
        from.setText(SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()))
        to = findViewById(R.id.to)
        to.setText(SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()))

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

    fun toDatePicker(view: View) {
        var cal = Calendar.getInstance()

        var today = System.currentTimeMillis() - 1000;


        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MM/yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                to.setText(sdf.format(cal.time))
            }

        to.setText(SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()))

        val dialog = DatePickerDialog(
            this, dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
        dialog.datePicker.maxDate = CalendarHelper.getCurrentDateInMills()
        dialog.show()
    }

    fun fromDatePicker(view: View) {
        var cal = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MM/yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                from.setText(sdf.format(cal.time))
            }

        from.setText(SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()))

        val dialog = DatePickerDialog(
            this, dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
        dialog.datePicker.maxDate = CalendarHelper.getCurrentDateInMills()
        dialog.show()
    }

    fun addItem(view: View) {
        println("addItem")

        // to get selected radio button
        val selectedId = rg.getCheckedRadioButtonId()

        var radioButton = findViewById(selectedId) as RadioButton

        var selected = radioButton.getText().toString()
        val item = Item()

        if (selected.toString().equals("Share", ignoreCase = true)) {
            item.type = ShareType.SHARE.value
        }

        if (selected.toString().equals("Lend", ignoreCase = true)) {
            item.type = ShareType.LEND.value
        }

        var format = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        if (!validateInsertedData()) {
            item.itemName = item_name.text.toString()
            item.availableFromDate = format.parse(from.text.toString())
            item.availableToDate = format.parse(to.text.toString())
            item.fees = fees.text.toString().toDouble()
            item.itemDescription = item_desc.text.toString()
            // item.itemImagePath = "drwable"
            item.type = ShareType.SHARE.value
            item.user = loggedInUser

            if (loggedInUser != null) {
                databaseReference.child("Items").push().setValue(item)
            }
        }
        goToMainActivity()
    }


    fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user", loggedInUser)
        startActivity(intent)
    }

}