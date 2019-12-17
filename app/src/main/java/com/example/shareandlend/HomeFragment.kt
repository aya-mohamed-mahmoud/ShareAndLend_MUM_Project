package com.example.shareandlend


import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    var layoutManager: RecyclerView.LayoutManager? = null
    var madr: MyAdapter? = null

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val context = inflater.context

        val list = arrayOf(
            Product("Mac Probook", 300.0, "silver", R.drawable.macprobook, 1, "6.5-inch Super Retina XDR display1\n" +
                        "IP68 Water and dust resistant2\n" +
                        "Triple 12MP rear cameras\n" +
                        "12MP TrueDepth front camera"),
            Product("Mac Mini", 300.0, "space gray", R.drawable.mac_mini, 1, "6.5-inch Super Retina XDR display1\n" +
                        "IP68 Water and dust resistant2\n" +
                        "Triple 12MP rear cameras\n" +
                        "12MP TrueDepth front camera"),
            Product("Iphone11", 300.0,"blue", R.drawable.iphone11, 1, " 6.5-inch Super Retina XDR display1\n" +
                        "IP68 Water and dust resistant2\n" +
                        "Triple 12MP rear cameras\n" +
                        "12MP TrueDepth front camera"),  Product("Mac Probook", 300.0, "silver", R.drawable.macprobook, 1, "6.5-inch Super Retina XDR display1\n" +
                       "IP68 Water and dust resistant2\n" +
                       "Triple 12MP rear cameras\n" +
                       "12MP TrueDepth front camera"),
            Product("Mac Mini", 300.0, "space gray", R.drawable.mac_mini, 1, "6.5-inch Super Retina XDR display1\n" +
                        "IP68 Water and dust resistant2\n" +
                        "Triple 12MP rear cameras\n" +
                        "12MP TrueDepth front camera"),
            Product("Iphone11", 300.0,"blue", R.drawable.iphone11, 1, " 6.5-inch Super Retina XDR display1\n" +
                        "IP68 Water and dust resistant2\n" +
                        "Triple 12MP rear cameras\n" +
                        "12MP TrueDepth front camera"),  Product("Mac Probook", 300.0, "silver", R.drawable.macprobook, 1, "6.5-inch Super Retina XDR display1\n" +
                        "IP68 Water and dust resistant2\n" +
                        "Triple 12MP rear cameras\n" +
                        "12MP TrueDepth front camera"),
            Product("Mac Mini", 300.0, "space gray", R.drawable.mac_mini, 1, "6.5-inch Super Retina XDR display1\n" +
                        "IP68 Water and dust resistant2\n" +
                        "Triple 12MP rear cameras\n" +
                        "12MP TrueDepth front camera"),
            Product("Iphone11", 300.0,"blue", R.drawable.iphone11, 1, " 6.5-inch Super Retina XDR display1\n" +
                        "IP68 Water and dust resistant2\n" +
                        "Triple 12MP rear cameras\n" +
                        "12MP TrueDepth front camera"))

        val rview: RecyclerView = view.findViewById(R.id.rv) as RecyclerView

        madr = MyAdapter(context, list)
        layoutManager = LinearLayoutManager(context)
        rview?.layoutManager = layoutManager
        rview?.adapter = madr

        return view
    }
}
