package com.aungthuya.codetest.network

import com.aungthuya.codetest.model.response.ExecutedResponse
import com.aungthuya.codetest.usecase.WondersUseCase

/**
 * This interface provide access to network services.
 *
 * @author Aung Thuya
 * @since 28 July 2019
 */
interface NetRepository {

    /**
     * Get all wonder places.
     *
     * @return Result object of wonder places.
     */
    fun getWonders(): ExecutedResponse<WondersUseCase.Result>
}