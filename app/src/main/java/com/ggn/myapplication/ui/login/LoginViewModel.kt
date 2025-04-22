package com.ggn.myapplication.ui.login

import androidx.lifecycle.viewModelScope
import com.ggn.myapplication.base.ViewModelG
import com.ggn.myapplication.base.validation.DataValidator
import com.ggn.myapplication.base.validation.ValidationResult
import com.ggn.myapplication.di.EmailValidatorInject
import com.ggn.myapplication.di.PasswordValidatorInject
import com.ggn.myapplication.domain.usecases.user.UserLogin
import com.ggn.myapplication.base.network.APIResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLogin: UserLogin,
//    private val protoPref: PreferencesHelper,
    @EmailValidatorInject private val emailValidator: DataValidator<String>,
    @PasswordValidatorInject private val passwordValidator: DataValidator<String>
    ) : ViewModelG<LoginFormState>() {

    override val mutableState: MutableStateFlow<LoginFormState> = MutableStateFlow(LoginFormState())

    fun onEvent(event: LoginScreenEvents) {
        viewModelScope.launch {
            when(event) {
                is LoginScreenEvents.EmailChanged -> {
                    updateUIState {
                        it.copy(email = event.value)
                    }
                }
                is LoginScreenEvents.PasswordChanged -> {
                    updateUIState {
                        it.copy(password = event.value)
                    }
                }
                is LoginScreenEvents.SubmitClicked -> {
                    validateAndHitAPI()
                }
            }
        }
    }


 private suspend fun validateAndHitAPI() {
            val email = state().value.email
            val password = state().value.password

            val validEmail = emailValidator.isValid(email)
            val validPassword = passwordValidator.isValid(password)

            val ifFailure = listOf(validEmail, validPassword)
                .any { it is ValidationResult.Failure }
            if (ifFailure) {
                updateUIState {
                    it.copy(emailError = if(validEmail is ValidationResult.Failure){validEmail.errorMsg}else{null} ,
                            passwordError = if(validPassword is ValidationResult.Failure){validPassword.errorMsg}else{null} )
                }
            }
            else{
                updateUIState {
                    it.copy(emailError = null ,
                        passwordError = null,
                    APIError = null)
                }
                userLogin(email,password,true).onEach{it->
                    delay(1000)
                    when (it) {
                        is APIResponse.Loading -> {
                            updateUIState {state->
                                state.copy(showLoading = true)
                            }
                        }
                        is APIResponse.Error   -> {
                            updateUIState {state->
                                state.copy(showLoading = false,
                                APIError = it.message)
                            }
                        }
                        is APIResponse.Success    -> {
                            updateUIState {state->
                                state.copy(showLoading = false,
                                    loginSuccessful = it.data
                                    )
                            }
                        }
                    }
                }.collect()
            }
 }




}