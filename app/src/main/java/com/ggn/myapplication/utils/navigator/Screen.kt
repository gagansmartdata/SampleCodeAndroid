package com.ggn.myapplication.utils.navigator

sealed class Screen(val route: String) {
	data object Login: Screen("login")
	data object Signup: Screen("signup")
	data object ForgotPassword: Screen("forgot_password")
}