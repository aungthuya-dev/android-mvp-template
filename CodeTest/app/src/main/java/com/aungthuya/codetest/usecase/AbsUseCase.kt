package com.aungthuya.codetest.usecase

import android.os.Handler
import android.os.Looper
import com.aungthuya.codetest.core.App
import com.aungthuya.codetest.model.response.ErrorThrowable
import com.aungthuya.codetest.model.response.ExecutedResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.android.Main

abstract class AbsUseCase<in Param, out Result>(protected val app: App) {

    fun execute(request: Param, success: (Result?) -> Unit, error: (ErrorThrowable) -> Unit) {
        @Suppress("DeferredResultUnused")
        GlobalScope.async(Dispatchers.Main, CoroutineStart.DEFAULT) {
            try {
                val response = executeAsync(request).await()
                when {
                    response.exception != null -> {
                        Handler(Looper.getMainLooper()).post {
                            error.invoke(response.exception!!)
                        }
                    }
                    else -> {
                            success.invoke(response.result)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun executeAsync(request: Param): Deferred<ExecutedResponse<out Result>> =
        GlobalScope.async(Dispatchers.Default, CoroutineStart.DEFAULT) {
            //        val b = Looper.getMainLooper() == Looper.myLooper()
//        Log.e("check if running in UI thread", b.toString())
            return@async execute(request)
        }

    protected abstract suspend fun execute(request: Param): ExecutedResponse<out Result>


}