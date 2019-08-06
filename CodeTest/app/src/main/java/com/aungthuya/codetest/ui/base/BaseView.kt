package com.aungthuya.codetest.ui.base

import android.content.Context

interface BaseView : ProgressView, ErrorView {

    fun provideContext(): Context

}