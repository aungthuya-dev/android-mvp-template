package com.aungthuya.codetest.network

import com.aungthuya.codetest.model.response.ErrorThrowable
import com.aungthuya.codetest.model.response.ExecutedResponse
import com.aungthuya.codetest.usecase.WondersUseCase
import retrofit2.Call
import retrofit2.Response

class NetRepositoryImpl(private val netManager: RetrofitManager) : NetRepository {

    private val api by lazy { netManager.getApi() }

    override fun getWonders(): ExecutedResponse<WondersUseCase.Result> {
        return processRequest(api.getWonders())
    }

    // Others

    private fun <T> processRequest(call: Call<T>): ExecutedResponse<T> {
        return try {
            processPositiveResponse(call.execute())
        } catch (ex: Exception) {
            println(ex)
            processNegativeResponse(ex)
        }
    }

    private fun <T> processPositiveResponse(response: Response<T>): ExecutedResponse<T> {
        val executedResponse: ExecutedResponse<T>

        executedResponse = if (response.isSuccessful) {
            val result = response.body()
            ExecutedResponse(result = result)
        } else {
            val exception = netManager.parseRetrofitException(response.errorBody(), ErrorThrowable::class.java)
            ExecutedResponse(result = null, exception = exception)
        }
        return executedResponse
    }

    private fun <T> processNegativeResponse(ex: Exception): ExecutedResponse<T> {
        // add some processing if needed
        return ExecutedResponse(result = null, exception = ErrorThrowable(ex.localizedMessage))
    }
}