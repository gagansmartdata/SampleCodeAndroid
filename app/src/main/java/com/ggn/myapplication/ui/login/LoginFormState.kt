package com.ggn.myapplication.ui.login

import com.ggn.myapplication.domain.model.UserData


data class LoginFormState(
    var email: String = "",
    var emailError: Any? = null,
    var password: String = "",
    var passwordError: Any? = null,
    var showLoading: Boolean = false,
    var APIError: Any? = null,
    var loginSuccessful: UserData? = null
)