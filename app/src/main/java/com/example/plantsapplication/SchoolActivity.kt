package com.example.plantsapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plantsapplication.adapter.SchoolAdapter
import com.example.plantsapplication.data.model.PlantModelImpl
import com.example.plantsapplication.data.vos.PlantVO
import com.example.plantsapplication.delegate.FragmentDelegateOne
import com.example.plantsapplication.mvp.presenter.PlantlistPresenter
import com.example.plantsapplication.mvp.presenter.SchoolPresenter
import com.example.plantsapplication.mvp.views.SchoolView

import kotlinx.android.synthetic.main.activity_school.*

class SchoolActivity : BaseActivity(), SchoolView{

    override fun displayPlantList(eventList: List<PlantVO>) {
        schoolAdapter.setNewData(eventList as MutableList<PlantVO>)
    }

    override fun navigateToDetail(id: String) {
       val intent=this?.let { PlantDetailActivity.newIntent(it,id)  }
       startActivity(intent)
    }

    override fun displayError(errorMessage: String) {
         Snackbar.make(rootview, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    private lateinit var mPresenter: SchoolPresenter

    private lateinit var schoolAdapter: SchoolAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school)

        mPresenter= SchoolPresenter()
        mPresenter.initPresenter(this)
        schoolAdapter = SchoolAdapter(mPresenter)

        with(rc_school)
        {
            setHasFixedSize(true)
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@SchoolActivity)
            rc_school.adapter = schoolAdapter
        }
        mPresenter.onCreate()
    }

    override fun onStart() {
        super.onStart()
        mPresenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        mPresenter.onResume()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestory()
    }

    override fun onPause() {
        super.onPause()
        mPresenter.onPause()
    }



//    override fun onClicked(id: String) {
//        val intent=this?.let { PlantDetailActivity.newIntent(it,id)  }
//        startActivity(intent)
//    }

}
