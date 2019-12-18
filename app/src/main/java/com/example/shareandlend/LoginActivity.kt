package com.example.shareandlend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import com.example.shareandlend.model.User
import kotlinx.android.synthetic.main.login.*

class LoginActivity : AppCompatActivity() {

    lateinit var firestore: FirebaseFirestore
    val USERS_COLLECTION: String = "Users"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        firestore = FirebaseFirestore.getInstance()
    }

    private fun validateInsertedData(): Boolean {
        var inValid = false
        if (email_lgn.text == null || !email_lgn.text.isNotBlank()) {
            inValid = true
            email_lgn.error = "Please insert email"
        }

        if (password_lgn.text == null || !password_lgn.text.isNotBlank()) {
            inValid = true
            password_lgn.error = "Please insert password"
        }
        return inValid
    }

    fun onClickLoginButton(view: View) {
        if (!validateInsertedData()) {
            firestore.collection(USERS_COLLECTION).document(email_lgn.text.toString()).get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.data != null) {
                        if (documentSnapshot.getString("password") != null && documentSnapshot.getString("password").equals(password_lgn.text.toString())) {
                            val intent = Intent(this, MainActivity::class.java)
                            val user = documentSnapshot.data as User
                            intent.putExtra("user",user)
                            startActivity(intent)
                        } else {
                            password_lgn.error = "Wrong password"
                        }
                    } else {
                        email_lgn.error = "No User is registered with this email"
                    }

                }
        }

    }


    fun goToCreateUserActivity(view: View) {
        val intent = Intent(this, CreateAccountActivity::class.java)
        startActivity(intent)
    }


}
