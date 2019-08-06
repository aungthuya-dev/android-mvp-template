package com.aungthuya.codetest.network

import com.aungthuya.codetest.usecase.WondersUseCase
import retrofit2.Call
import retrofit2.http.GET

/**
 * This interface provide all the web service apis required by this application.
 *
 * @author Aung Thuya
 * @since 28 July 2019
 */
interface Api {

    /**
     * Get all wonder places.
     *
     * @return Result object of wonder places.
     */
    @GET("bins/13g69v")
    fun getWonders(): Call<WondersUseCase.Result>
}