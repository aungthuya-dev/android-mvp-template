package com.aungthuya.codetest.usecase

import com.aungthuya.codetest.core.App
import com.aungthuya.codetest.model.Wonder
import com.aungthuya.codetest.model.response.ExecutedResponse
import com.google.gson.annotations.SerializedName

class WondersUseCase(app: App): AbsUseCase<Unit, WondersUseCase.Result>(app) {

    override suspend fun execute(request: Unit): ExecutedResponse<out Result> {
        val result = app.getNetManager().getWonders()
        result.result?.let {
            app.getStorage().saveWonders(it.wonders)
        }
        return result
    }

    class Result(
        @SerializedName("wonders") val wonders: List<Wonder>
    )
}