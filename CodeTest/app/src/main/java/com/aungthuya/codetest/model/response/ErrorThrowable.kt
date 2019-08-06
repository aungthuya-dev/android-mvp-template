package com.aungthuya.codetest.model.response

import com.google.gson.annotations.SerializedName

open class ErrorThrowable(
//    @SerializedName("statusCode") var status: Int = -1,
    @SerializedName("message") override var message: String = "Unexpected Error"
) : Throwable()