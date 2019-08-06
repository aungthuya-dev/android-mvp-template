package com.aungthuya.codetest.ui.wonders

import com.aungthuya.codetest.core.App
import com.aungthuya.codetest.ui.base.PresenterSubscriber
import com.aungthuya.codetest.usecase.WondersUseCase

class WondersPresenterImpl(app: App): PresenterSubscriber<WondersContract.WondersView>(app), WondersContract.WondersPresenter {

    private val wondersUseCase by lazy { WondersUseCase(app) }

    override fun loadWonders() {
        wondersUseCase.execute(
            Unit,
            {
                view?.loadedWonders(it!!.wonders)
            },
            {
                view?.showError(it)
                view?.loadingFailed()
            }
        )
    }
}