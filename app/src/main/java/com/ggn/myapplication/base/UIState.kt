package com.ggn.myapplication.base

/**
 * Base data class for UI state
 */
data class ApiResponseWrapper<G>(
    val data: G? = null,
    val statusCode: Int? = null,
    val message: String? = null
){
    fun isSuccessful() = statusCode in 200..299
}

