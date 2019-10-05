package com.example.plantsapplication.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.plantsapplication.viewholders.BaseViewHolder
import java.util.ArrayList

abstract class BaseAdapter <VH: BaseViewHolder<T>,T>: RecyclerView.Adapter<VH>()
{
    private var datalist:MutableList<T> = ArrayList()


    override fun getItemCount(): Int =datalist.size
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.data=datalist[position]
    }

    fun setNewData(newData : MutableList<T>){
        datalist=newData
        notifyDataSetChanged()
    }

    fun appendNewData(newData:List<T>)
    {
        datalist.addAll(newData)
        notifyDataSetChanged()
    }
}