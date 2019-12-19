package com.example.shareandlend

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shareandlend.model.Item
import com.example.shareandlend.model.ShareType
import com.google.firebase.database.*

class UserLendItemsActivity : AppCompatActivity() {

    var layoutManager: RecyclerView.LayoutManager? = null
    var madr: MyAdapter? = null

    val list: ArrayList<Item>? = ArrayList()


    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_lend_items)

        databaseReference = FirebaseDatabase.getInstance().reference.child("Items")

        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // Get item object and use the values to update the UI

                for (data in dataSnapshot.children) {

                    var item = data.getValue(Item::class.java)


                    if(item?.type!=null && item!!.type!! == ShareType.LEND.value){
                        list!!.add(item!!)
                    }

                }
                val rview: RecyclerView = findViewById(R.id.recycler_view_lend) as RecyclerView

                madr = MyAdapter(this@UserLendItemsActivity, list!!.toTypedArray())

                layoutManager = LinearLayoutManager(this@UserLendItemsActivity)

                rview?.layoutManager = layoutManager

                rview?.adapter = madr

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Toast.makeText(this@UserLendItemsActivity, "cancel", Toast.LENGTH_SHORT).show()
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException())


            }
        })

    }

}