package com.aungthuya.codetest.ui.base

interface PresenterFactory<out P> {
    fun providePresenter(): P
}