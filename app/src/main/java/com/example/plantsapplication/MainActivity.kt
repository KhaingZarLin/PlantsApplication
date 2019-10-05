package com.example.plantsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.plantsapplication.data.vos.PlantVO
import com.example.plantsapplication.mvp.presenter.PlantlistPresenter
import com.example.plantsapplication.mvp.views.PlantlistView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity(),PlantlistView {
    override fun displayPlantList(eventList: List<PlantVO>) {

    }

    override fun navigateToDetail(eventId: Int) {

    }

    override fun displayError(errorMessage: String) {

    }

    private lateinit var mPresenter:PlantlistPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter= PlantlistPresenter(this)

        txt_email_id.text
        ed_password.text


        btn_login.setOnClickListener(View.OnClickListener {
            val intent= Intent(this, SchoolActivity::class.java)
            startActivity(intent)

        })
    }
}
