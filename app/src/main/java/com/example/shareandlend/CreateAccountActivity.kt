package com.example.shareandlend

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shareandlend.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.create_new_account.*

class CreateAccountActivity : AppCompatActivity() {

    lateinit var firestore: FirebaseFirestore
    val USERS_COLLECTION: String = "Users"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_account)
        firestore = FirebaseFirestore.getInstance()
    }

    private fun validateInsertedData() : Boolean{
        var inValid = false
        if (email.text == null || !email.text.isNotBlank()){
            inValid = true
            email.error = "Please insert email"
        }

        if (password.text == null || !password.text.isNotBlank()){
            inValid = true
            password.error = "Please insert password"
        }

        if (user_name.text == null || !user_name.text.isNotBlank()){
            inValid = true
            user_name.error = "Please insert user name"
        }

        if (phone_number.text == null || !phone_number.text.isNotBlank()){
            inValid = true
            phone_number.error = "Please insert phone number"
        }
        return inValid
    }

    fun onClickButton(view: View) {
        if (!validateInsertedData()) {
            val user = User()
            user.name = user_name.text.toString()
            user.password = password.text.toString()
            user.dormNo = dorm_number.text.toString().toInt()
            user.email = email.text.toString()
            user.phoneNo = phone_number.text.toString().toInt()


            firestore.collection(USERS_COLLECTION).document(user.email.toString()).get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.data != null) {
                        Toast.makeText(
                            this,
                            "User already registered with this email",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        firestore.collection(USERS_COLLECTION).document(user.email.toString())
                            .set(user).addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        this,
                                        "User add successfully ! ",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }

                            }
                    }
                }
        }
    }
}