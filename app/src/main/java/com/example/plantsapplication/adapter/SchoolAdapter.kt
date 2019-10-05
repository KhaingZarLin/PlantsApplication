package com.example.plantsapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plantsapplication.R
import com.example.plantsapplication.data.vos.PlantVO
import com.example.plantsapplication.delegate.FragmentDelegateOne
import com.example.plantsapplication.viewholders.SchoolViewHolder

class SchoolAdapter(private val itemClicked: FragmentDelegateOne):
    BaseAdapter<SchoolViewHolder, PlantVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flower_item_view, parent, false)
        return SchoolViewHolder(view, itemClicked)
    }
}
/*
class SchoolAdapter: RecyclerView.Adapter<SchoolViewHolder>() {

    private var fragmentDelegateOne: FragmentDelegateOne? = null

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {

    }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.flower_item_view, parent, false)
        return SchoolViewHolder(view)
    }
    fun SchoolAdapter(fragDelegate1: FragmentDelegateOne){
        this.fragmentDelegateOne = fragDelegate1
    }

}*/
