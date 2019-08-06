package com.aungthuya.codetest.ui.base

import android.text.TextUtils
import android.util.Log
import android.util.LruCache

class PresenterManager {

    private val cache: LruCache<String, BasePresenter<*>> = LruCache(12)

    @Suppress("UNCHECKED_CAST")
    fun <V : BaseView, P : BasePresenter<V>> getPresenter(
        key: String,
        factory: PresenterFactory<P>
    ): P {
        var presenter: P? = null
        try {
            presenter = cache[key] as P
        } catch (e: ClassCastException) {
            Log.w("PresenterManager", "Duplicate presenter with key: $key")
        }
        if (presenter == null) {
            presenter = factory.providePresenter()
            cache.put(key, presenter)
        }
        return presenter
    }

    fun remove(key: String?) {
        if (!TextUtils.isEmpty(key))
            cache.remove(key)
    }

    companion object {
        val instance: PresenterManager by lazy {
            synchronized(PresenterManager::class.java) {
                PresenterManager()
            }
        }
    }
}