package com.example.myapplication.Login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class RecyclerViewAdapter(private val studentList:List<Student>):
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>()
{
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val classTextView: TextView = itemView.findViewById(R.id.classTextView)
        val rollNoTextView: TextView = itemView.findViewById(R.id.rollNoTextView)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        val item=studentList[position]
        holder.nameTextView.text=item.name
        holder.classTextView.text=item.className.toString()
        holder.rollNoTextView.text=item.rollNo.toString()
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
}