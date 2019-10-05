package com.example.plantsapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.plantsapplication.R
import com.example.plantsapplication.data.vos.PlantTipsVO
import com.example.plantsapplication.data.vos.PlantVO
import com.example.plantsapplication.delegate.FragmentDelegateOne
import com.example.plantsapplication.viewholders.PlantDetailViewHolder


class PlantDetailAdapter():
    BaseAdapter<PlantDetailViewHolder, PlantVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantDetailViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.detail_item_view,parent, false)
        return PlantDetailViewHolder(itemView)
    }

}
