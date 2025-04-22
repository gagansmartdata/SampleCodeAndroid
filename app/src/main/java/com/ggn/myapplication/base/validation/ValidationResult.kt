package com.ggn.myapplication.base.validation

sealed class ValidationResult {
    data object Success : ValidationResult()
    data class Failure(val errorMsg: Any) : ValidationResult()//error msg can be string or int res
}