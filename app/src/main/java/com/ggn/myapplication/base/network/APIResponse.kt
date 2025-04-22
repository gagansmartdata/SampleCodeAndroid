package com.ggn.myapplication.base.network

sealed interface APIResponse<T> {
    class Success<T>(val data: T,val message: Any? = null) : APIResponse<T>
    //message is of Any type, it can be res int or string, so we can use them accordingly.
    class Error<T>(val message: Any,val data: T? = null) : APIResponse<T>
    class Loading<T>(val showLoader : Boolean = true) : APIResponse<T>
}