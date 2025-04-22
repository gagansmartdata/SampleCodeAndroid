package com.ggn.myapplication.common

import com.ggn.myapplication.R
import com.ggn.myapplication.base.ApiResponseWrapper
import com.ggn.myapplication.base.network.APIResponse
import retrofit2.HttpException


fun <G> ApiResponseWrapper<G>.returnDataOrError(): APIResponse<G?> {
    return if(isSuccessful()) {
        APIResponse.Success<G?>(data, message)
    }
    else {
        message?.let {
            APIResponse.Error<G?>(it)
        } ?: APIResponse.Error<G?>(R.string.network_error)
    }
}


fun HttpException.getAPIErrorMsg(): String? {
/*    val moshi = Gson.Builder().build()
    val jsonAdapter: JsonAdapter<_ApiResponseWrapper> =
        moshi.adapter(_ApiResponseWrapper::class.java)*/
    return response()?.errorBody()?.string()
}
