package com.aungthuya.codetest.core

import android.content.Context
import com.aungthuya.codetest.network.NetRepository
import com.aungthuya.codetest.network.RetrofitManager
import com.aungthuya.codetest.storage.LocalRepository

/**
 * This interface declare general methods to get access from the whole application.
 *
 * @author Aung Thuya
 * @since 2019-06-11
 */
interface App {

    /**
     * Get the instance of application context.
     * This context is require to get access to android resources.
     *
     * @return Instance of application context
     */
    fun getContext(): Context

    /**
     * This method is only implemented for Instrumented testing.
     * So, test case can set custom RetrofitManager.
     *
     * @param netManager Instance of RetrofitManager
     */
    fun setNetManager(netManager: RetrofitManager)

    /**
     * Getter method of NetRepository.
     * NetRepository include all available endpoints of backend APIs.
     *
     * @return Instance of NetRepository
     */
    fun getNetManager(): NetRepository

    /**
     * Get the instance of LocalRepository that has access to SharedPreferences.
     * LocalRepository already implemented required methods to saved and load information.
     *
     * @return Instance of LocalRepository
     */
    fun getStorage(): LocalRepository

}