package com.aungthuya.codetest.ui.base

interface BasePresenter<in T : BaseView?> {

    fun bindView(view: T)
    fun unbindView()

}