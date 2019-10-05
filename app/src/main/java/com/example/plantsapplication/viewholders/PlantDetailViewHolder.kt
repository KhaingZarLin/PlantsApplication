package com.example.plantsapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantsapplication.data.vos.PlantVO
import com.example.plantsapplication.delegate.FragmentDelegateOne
import kotlinx.android.synthetic.main.activity_plant_detail.view.*
import kotlinx.android.synthetic.main.flower_item_view.view.*


class PlantDetailViewHolder(itemView: View) :
    BaseViewHolder<PlantVO>(itemView) {

    init {
        itemView.plant_img.setOnClickListener {
            val id = data?.id
            if (id != null){
            //    itemClicked.onClicked(id)
            }

        }
    }
    override fun bindData(data: PlantVO) {

        itemView.txt_plant_name.text = data.plantName
        Glide.with(itemView.plant_img).load(data.plantPhoto).into(itemView.plant_img)
        Glide.with(itemView. vpEventDetailImages).load(data.plantPhoto).into(itemView. vpEventDetailImages)

    }
}


