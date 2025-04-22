package com.ggn.myapplication.domain.repository

import com.ggn.myapplication.base.ApiResponseWrapper
import com.ggn.myapplication.data.remote.apipayload.SignupPayload
import com.ggn.myapplication.domain.model.UserData

interface UserRepository {

    suspend fun loginUser(
        email: String,
        password: String,
        isTerminateExistingSession: Boolean
    ): ApiResponseWrapper<UserData>

    suspend fun signupUser(signupPayload: SignupPayload): ApiResponseWrapper<UserData>


    suspend fun forgotPassword(
        email: String
    ): ApiResponseWrapper<Any>
}
