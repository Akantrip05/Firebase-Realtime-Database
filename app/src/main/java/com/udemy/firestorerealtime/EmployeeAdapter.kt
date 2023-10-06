package com.udemy.firestorerealtime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udemy.firestorerealtime.R.layout.items

class EmployeeAdapter(private val empList: ArrayList<EmployeeModel>):RecyclerView.Adapter<MyViewHolder>() {

    lateinit var clickItem:onItemClickListener
    interface onItemClickListener{
     fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(clickListener: onItemClickListener){
        clickItem = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false)
        return MyViewHolder(view,clickItem)
    }

    override fun getItemCount(): Int {
        return empList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val things = empList[position]
        holder.emplName.text = things.name
    }
}
class MyViewHolder(itemsView:View , clickListener: EmployeeAdapter.onItemClickListener):RecyclerView.ViewHolder(itemsView) {
   val emplName : TextView = itemsView.findViewById(R.id.text)

    init {
        itemsView.setOnClickListener(){
            clickListener.onItemClick(adapterPosition)
        }
    }
}
