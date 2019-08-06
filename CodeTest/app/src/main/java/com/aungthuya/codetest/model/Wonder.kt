package com.aungthuya.codetest.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import java.io.Serializable

open class Wonder: RealmObject(), Serializable {
    @SerializedName("location") open var location: String = ""
    @SerializedName("description") open var description: String = ""
    @SerializedName("image") open var imageUrl: String = ""
//    @SerializedName("lat") open var latitude: Double = 0.0
//    @SerializedName("long") open var longitude: Double = 0.0
}