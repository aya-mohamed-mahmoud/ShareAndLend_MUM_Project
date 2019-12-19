package com.example.shareandlend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shareandlend.model.Item
import com.example.shareandlend.model.ShareType
import com.google.firebase.database.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AvailableSharedItemsFragment : Fragment() {

    var layoutManager: RecyclerView.LayoutManager? = null
    var madr: MyAdapter? = null

    private lateinit var database: DatabaseReference

    var items: ArrayList<Item> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        database = FirebaseDatabase.getInstance().reference
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.available_shared_items_fragment, container, false)

        val context = inflater.context

        database.child("Items")
            .addValueEventListener(object : ValueEventListener {
                var item: Item? = Item()
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    items = arrayListOf()
                    // Get Post object and use the values to update the UI
                    for (snapshot in dataSnapshot.children) {
                        item = snapshot.getValue(Item::class.java)
                        val dateFormat =
                            SimpleDateFormat("dd/MM/yyy")
                        val fromDateStr  = dateFormat.format(item!!.availableFromDate)
                        val fromFormatedDate = dateFormat.parse(fromDateStr)
                        val toDateStr  = dateFormat.format(item!!.availableToDate)
                        val toFormatedDate = dateFormat.parse(toDateStr)
                        val nowDateStr = dateFormat.format(Date())
                        val nowDate = dateFormat.parse(nowDateStr)

                        if (item!!.type != null && item!!.type!! == ShareType.SHARE.value ){
                            if(fromFormatedDate.compareTo(nowDate) >= 0 && toFormatedDate.compareTo(nowDate) >= 0) {
                                items.add(item!!)
                            }
                        }
                    }

                    val rview: RecyclerView = view.findViewById(R.id.rv) as RecyclerView

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
