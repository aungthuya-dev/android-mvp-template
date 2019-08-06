package com.aungthuya.codetest.ui.wonders

import com.aungthuya.codetest.model.Wonder
import com.aungthuya.codetest.ui.base.BasePresenter
import com.aungthuya.codetest.ui.base.BaseView

interface WondersContract {
    interface WondersView: BaseView {
        fun loadedWonders(wonders: List<Wonder>)
        fun loadingFailed()
    }

    interface WondersPresenter: BasePresenter<WondersView> {
        fun loadWonders()
    }
}