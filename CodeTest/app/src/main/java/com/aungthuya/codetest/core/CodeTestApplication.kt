package com.aungthuya.codetest.core

import android.app.Application
import android.content.Context
import com.aungthuya.codetest.network.NetRepository
import com.aungthuya.codetest.network.NetRepositoryImpl
import com.aungthuya.codetest.network.RetrofitManager
import com.aungthuya.codetest.storage.LocalRepository
import com.aungthuya.codetest.storage.LocalRepositoryImpl
import io.realm.Realm
import io.realm.RealmConfiguration

class CodeTestApplication: Application(), App {

    private val storage by lazy { LocalRepositoryImpl() }
    private var net: NetRepositoryImpl? = null

    override fun onCreate() {
        super.onCreate()

        // Initialize realm database
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }

    override fun getContext(): Context = applicationContext

    override fun getStorage(): LocalRepository = storage

    override fun setNetManager(netManager: RetrofitManager) {
        net = NetRepositoryImpl(netManager)
    }

    override fun getNetManager(): NetRepository {
        if(net == null) {
            net = NetRepositoryImpl(RetrofitManager())
        }
        return net!!
    }

    companion object {
        /**
         * Get the instance of App to get access to provided methods.
         *
         * @param context Application context.
         * @return the Instance of App
         */
        @JvmStatic
        fun getApp(context: Context): App = context.applicationContext as App
    }

}