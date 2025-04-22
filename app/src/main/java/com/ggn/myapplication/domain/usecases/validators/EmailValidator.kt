package com.ggn.myapplication.domain.usecases.validators

import com.ggn.myapplication.R
import com.ggn.myapplication.base.validation.DataValidator
import com.ggn.myapplication.base.validation.ValidationResult


class EmailValidator : DataValidator<String> {

    override fun isValid(dataToValidate: String): ValidationResult {

        return when {
            dataToValidate.isEmpty() -> {
                ValidationResult.Failure(R.string.network_error)//pass string msg or resource id & handle them according to type
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(dataToValidate)
                .matches()           -> {
                ValidationResult.Failure("Please enter a valid email")
            }
            else                     -> {
                ValidationResult.Success
            }
        }

    }
}