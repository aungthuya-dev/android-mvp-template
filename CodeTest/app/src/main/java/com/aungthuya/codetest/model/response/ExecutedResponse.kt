package com.aungthuya.codetest.model.response

data class ExecutedResponse<T>(
    var result: T? = null,
    var exception: ErrorThrowable? = null
)