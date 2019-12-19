package com.example.shareandlend

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shareandlend.model.Item
import com.example.shareandlend.model.ShareType
import com.example.shareandlend.model.User
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class AvailableLendItemsFragment : Fragment() {

    var layoutManager: RecyclerView.LayoutManager? = null
    var madr: MyAdapter? = null

    private lateinit var database: DatabaseReference

    var items : ArrayList<Item> = arrayListOf()

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        database = FirebaseDatabase.getInstance().reference
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val context = inflater.context
        
        val rview: RecyclerView = view.findViewById(R.id.rv) as RecyclerView

        database.child("Items")
            .addValueEventListener(object : ValueEventListener {
            var item: Item? = Item()
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for (snapshot in dataSnapshot.children) {
                    item = snapshot.getValue(Item::class.java)
                    if (item!!.type != null && item!!.type!! == ShareType.LEND.value) {
                        if (!items.contains(item!!)) {
                            items.add(item!!)
                        }
                    }
                }



                madr = MyAdapter(context, items.toTypedArray())
                layoutManager = LinearLayoutManager(context)
                rview?.layoutManager = layoutManager
                rview?.adapter = madr

            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })


        return view
    }
}
