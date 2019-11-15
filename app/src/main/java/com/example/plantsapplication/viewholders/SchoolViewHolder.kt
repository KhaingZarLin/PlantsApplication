package com.example.plantsapplication.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.example.plantsapplication.data.vos.PlantVO
import com.example.plantsapplication.delegate.FragmentDelegateOne
import kotlinx.android.synthetic.main.flower_item_view.view.*

class SchoolViewHolder(itemView: View, private val itemClicked: FragmentDelegateOne) :
    BaseViewHolder<PlantVO>(itemView) {

    init {
                itemView.setOnClickListener {
                    val id = data?.id
                    if (id != null){
                        itemClicked.onClicked(id,itemView.plant_img)
                    }

        }
    }
    override fun bindData(data: PlantVO) {

        itemView.txt_plant_name.text = data.plantName
        Glide.with(itemView).load(data.plantPhoto).into(itemView.plant_img)
        Glide.with(itemView).load(data.plantUploadedUserVO.userPhoto).into(itemView.img_profile)
        itemView.profile_name.text="by "+data.plantUploadedUserVO.name
    }
}
