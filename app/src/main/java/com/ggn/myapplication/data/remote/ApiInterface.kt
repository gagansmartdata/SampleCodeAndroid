package com.ggn.myapplication.data.remote

import com.ggn.myapplication.base.ApiResponseWrapper
import com.ggn.myapplication.data.remote.apipayload.SignupPayload
import com.ggn.myapplication.data.remote.dto.UserDataDto
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("/user/web/v1/signin")
    suspend fun login(@Field("email") email: String,
                      @Field("password") password: String,
                      @Field("isTerminateExistingSession") isTerminateExistingSession : Boolean): ApiResponseWrapper<UserDataDto>

    @POST("/user/web/v1/signup")
    suspend fun signup(@Body signupPayload: SignupPayload): ApiResponseWrapper<UserDataDto>

    @FormUrlEncoded
    @POST("/user/web/v1/forgotPassword")
    suspend fun forgotPassword(@Field("email") email: String): ApiResponseWrapper<UserDataDto>
}