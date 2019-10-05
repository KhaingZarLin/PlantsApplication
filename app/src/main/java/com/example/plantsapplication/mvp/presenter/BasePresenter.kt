package com.example.plantsapplication.mvp.presenter

import com.example.plantsapplication.mvp.views.BaseView

abstract class BasePresenter<T: BaseView> {

    protected lateinit var mView: T

    open fun initPresenter(view:T)
    {
        this.mView=view
    }
    open fun onStart(){}
    open fun onCreate(){}
    open fun onResume(){}
    open fun onPause(){}
    open fun onStop(){}
    open fun onDestory(){}

}