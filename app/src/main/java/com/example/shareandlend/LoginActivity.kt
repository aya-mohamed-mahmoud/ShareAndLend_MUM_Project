package com.example.shareandlend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
    }

    fun goToCreateUserActivity(view : View){
        val intent = Intent(this,CreateAccountActivity::class.java)
        startActivity(intent)
    }

    
}
