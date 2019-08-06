package com.aungthuya.codetest.ui.base

import com.aungthuya.codetest.model.response.ErrorThrowable

interface ErrorView {
    fun showError(error: String)

    fun showError(throwable: ErrorThrowable)
}