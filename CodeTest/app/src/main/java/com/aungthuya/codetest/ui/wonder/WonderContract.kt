package com.aungthuya.codetest.ui.wonder

import com.aungthuya.codetest.ui.base.BasePresenter
import com.aungthuya.codetest.ui.base.BaseView

interface WonderContract {
    interface WonderView: BaseView

    interface WonderPresenter: BasePresenter<WonderView>
}