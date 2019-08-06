package com.aungthuya.codetest.ui.base

import com.aungthuya.codetest.core.App

open class PresenterSubscriber<T : BaseView?>(var app: App) : BasePresenter<T> {

    var view: T? = null

    override fun bindView(view: T) {
        this.view = view
    }

    override fun unbindView() {
        this.view = null
    }
}