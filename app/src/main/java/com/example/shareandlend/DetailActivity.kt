package com.example.shareandlend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.item_details.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_details)
        // To avoid application crash check intent has data or not
        if(intent.hasExtra("image") && intent.hasExtra("name")&& intent.hasExtra("detail")){
            var ig =intent.getIntExtra("image",0)
            var t1 = intent.getStringExtra("name")
            var t2 = intent.getStringExtra("detail")
            name.text = t1.toString()
            //desc.text = t2.toString()
            imageView.setImageResource(ig)
        }
    }
}