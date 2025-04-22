package com.ggn.myapplication.domain.usecases.user

import com.ggn.myapplication.R
import com.ggn.myapplication.base.network.APIResponse
import com.ggn.myapplication.common.getAPIErrorMsg
import com.ggn.myapplication.common.returnDataOrError
import com.ggn.myapplication.data.remote.apipayload.SignupPayload
import com.ggn.myapplication.domain.model.UserData
import com.ggn.myapplication.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserSignup @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(signupPayload: SignupPayload): Flow<APIResponse<UserData?>> =
    flow {
        try {
            emit(APIResponse.Loading<UserData?>())
            emit(repository.signupUser(signupPayload).returnDataOrError())
        } catch(e: HttpException) {
            e.getAPIErrorMsg()?.let {
                emit(APIResponse.Error<UserData?>(it))
            }   ?: R.string.network_error
        } catch(e: IOException) {
            emit(APIResponse.Error<UserData?>(R.string.network_error))
        }
    }
}