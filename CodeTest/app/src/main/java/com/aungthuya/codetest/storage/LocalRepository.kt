package com.aungthuya.codetest.storage

import com.aungthuya.codetest.model.Wonder

/**
 * This class provide access to local storage.
 *
 * @author Aung Thuya
 * @since 28 July 2019
 */
interface LocalRepository {

    /**
     * Save wonder places in local storage.
     *
     * @param wonders Wonder places
     */
    fun saveWonders(wonders: List<Wonder>)

    /**
     * Get wonder places from local storage.
     *
     * @return Wonder places
     */
    fun getWonders(): List<Wonder>
}