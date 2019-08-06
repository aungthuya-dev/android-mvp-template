package com.aungthuya.codetest.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitManager {

    private var api: Api
    private var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(createGsonConverter()))
            .baseUrl(BASE_URL)
            .client(createApiClient())
            .build()

        api = retrofit.create(Api::class.java)
    }

    fun getApi(): Api = api

    private fun createGsonConverter(): Gson {
        return GsonBuilder()
            .enableComplexMapKeySerialization()
            .serializeNulls()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setPrettyPrinting()
            .setVersion(1.0)
            .create()
    }

    private fun createApiClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .authenticator(TokenAuthenticator())
            .retryOnConnectionFailure(true)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val url = originalRequest.url()
                    .newBuilder()
                    .build()

                val builder = originalRequest.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("xRetryCount", "0")

//                app.getLocalStorage().getAuthToken()?.let {
//                    builder.addHeader("Authorization", it)
//                }

                builder.url(url)
                val request = builder.build()
                chain.proceed(request)
            }
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .followRedirects(false)

        return builder.build()
    }

    fun <T> parseRetrofitException(errorBody: ResponseBody?, type: Class<T>): T {
        val converter = retrofit.responseBodyConverter<T>(type, arrayOfNulls(0))
        return converter.convert(errorBody!!)
    }

    private inner class TokenAuthenticator : Authenticator {

        override fun authenticate(route: Route?, response: Response?): Request? {

            val retryCount = response?.request()?.header("xRetryCount")?.toInt() ?: 0
            if (retryCount > 5)
                return null

            return response?.request()
                ?.newBuilder()
                ?.addHeader("xRetryCount", "${retryCount + 1}")
                ?.build()
        }
    }

    companion object {
        // Network request timeout duration
        private const val TIMEOUT_SECONDS: Long = 120

        // Base url of network API endpoint
        private const val BASE_URL = "https://api.myjson.com/"

    }

}


