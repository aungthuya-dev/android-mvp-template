package com.aungthuya.codetest.ui.wonder

import com.aungthuya.codetest.core.App
import com.aungthuya.codetest.ui.base.PresenterSubscriber

class WonderPresenterImpl(app: App): PresenterSubscriber<WonderContract.WonderView>(app), WonderContract.WonderPresenter