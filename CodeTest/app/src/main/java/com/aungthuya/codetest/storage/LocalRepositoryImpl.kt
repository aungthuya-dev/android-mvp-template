package com.aungthuya.codetest.storage

import com.aungthuya.codetest.model.Wonder
import io.realm.Realm

class LocalRepositoryImpl: LocalRepository {

    override fun saveWonders(wonders: List<Wonder>) {
        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.where(Wonder::class.java).findAll().deleteAllFromRealm() // clear all existing data first
            realm.copyToRealm(wonders)
            realm.commitTransaction()
        }
    }

    override fun getWonders(): List<Wonder> {
        Realm.getDefaultInstance().use { realm ->
            val wonders = realm.where(Wonder::class.java).findAll()
            return realm.copyFromRealm(wonders)
        }
    }
}