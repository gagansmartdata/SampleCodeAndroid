package com.ggn.myapplication.domain.usecases.user

import com.ggn.myapplication.R
import com.ggn.myapplication.base.network.APIResponse
import com.ggn.myapplication.common.getAPIErrorMsg
import com.ggn.myapplication.common.returnDataOrError
import com.ggn.myapplication.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ForgotPassword @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(email: String): Flow<APIResponse<Any?>> =
    flow {
        try {
            emit(APIResponse.Loading())
            emit(repository.forgotPassword(email).returnDataOrError())
        } catch(e: HttpException) {
            e.getAPIErrorMsg()?.let {
                emit(APIResponse.Error(it))
            }   ?: R.string.network_error
        } catch(e: IOException) {
            emit(APIResponse.Error(R.string.network_error))
        }
    }
}