package com.example.specialeffects.behavior

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.specialeffects.R
import kotlinx.android.synthetic.main.activity_recy_flat.*
import kotlinx.android.synthetic.main.item_behavior.view.*

/**
 * @author tengfei
 * date 2019-09-20 12:32
 * email tengfeigo@outlook.com
 * description
 */
class CustomBehaviorActivity : AppCompatActivity() {

    private val list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recy_flat)

        for (index in 0..20) {
            list.add(index.toString())
        }

        recycleView.layoutManager = LinearLayoutManager(this)


        recycleView.adapter = MyAdapter(list)
    }

}

class MyAdapter(private val list: ArrayList<String>) : RecyclerView.Adapter<MyAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_behavior, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.itemView.itemBehaviorText.text = list[position]
    }


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}