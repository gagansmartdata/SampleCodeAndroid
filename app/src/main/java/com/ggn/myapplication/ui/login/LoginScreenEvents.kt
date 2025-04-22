package com.ggn.myapplication.ui.login

sealed class LoginScreenEvents{
    class EmailChanged(val value : String) : LoginScreenEvents()
    class PasswordChanged(val value : String) : LoginScreenEvents()
    object SubmitClicked : LoginScreenEvents()
}
