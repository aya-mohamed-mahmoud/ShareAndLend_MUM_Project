package com.example.shareandlend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.widget.*
import com.example.shareandlend.model.Item


class MyAdapter(var context:Context, var productList :ArrayList<Item?>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //holder.im.setImageResource(productList[position].image)
        holder.t1.text = productList[position]?.itemName
        holder.t2.text = productList[position]?.itemDescription

        holder.parentlayout.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)

            var res = productList[position]

            Toast.makeText(context," $res clicked",Toast.LENGTH_LONG).show()
           // intent.putExtra("image", productList[position].image)
            intent.putExtra("name", productList[position]?.itemName)
            intent.putExtra("detail",productList[position]?.itemDescription)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.card_layout, parent, false)
        return MyViewHolder(v);
    }
    override fun getItemCount(): Int {
        return productList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var t1: TextView = itemView.findViewById<TextView>(R.id.tv1)
        var t2: TextView = itemView.findViewById<TextView>(R.id.tv2)
       // var im : ImageView = itemView.findViewById<ImageView>(R.id.imageView)
        var parentlayout : RelativeLayout = itemView.findViewById(R.id.playout) as RelativeLayout
    }

}