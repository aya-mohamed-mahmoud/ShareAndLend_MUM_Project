package com.example.shareandlend

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shareandlend.model.Item
import com.example.shareandlend.model.ShareType
import com.google.firebase.database.*

class UserSharedItemsActivity : AppCompatActivity() {

    var layoutManager: RecyclerView.LayoutManager? = null
    var madr: MyAdapter? = null

    val list: ArrayList<Item>? = ArrayList()


    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_shared_items)

        databaseReference = FirebaseDatabase.getInstance().reference.child("Items")

        databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // Get item object and use the values to update the UI

                for (data in dataSnapshot.children) {

                    var item = data.getValue(Item::class.java)
                    item!!.itemId = data.key

                    if(item?.type!=null && item!!.type!! == ShareType.SHARE.value){
                        list!!.add(item!!)
                    }

                }
                val rview: RecyclerView = findViewById(R.id.recycler_view_share) as RecyclerView

                madr = MyAdapter(this@UserSharedItemsActivity, list!!.toTypedArray())

                layoutManager = LinearLayoutManager(this@UserSharedItemsActivity)

                rview?.layoutManager = layoutManager

                rview?.adapter = madr

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Toast.makeText(this@UserSharedItemsActivity, "cancel", Toast.LENGTH_SHORT).show()
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException())


            }
        })

    }

}