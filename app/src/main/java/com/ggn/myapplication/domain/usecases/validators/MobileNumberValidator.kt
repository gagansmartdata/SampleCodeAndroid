package com.ggn.myapplication.domain.usecases.validators

import com.ggn.myapplication.base.validation.DataValidator
import com.ggn.myapplication.base.validation.ValidationResult


class MobileNumberValidator : DataValidator<String> {

    override fun isValid(dataToValidate: String): ValidationResult {

        return when {
            dataToValidate.isEmpty() -> {
                ValidationResult.Failure("Please enter mobile number.")//pass string msg or resource id & handle them according to type
            }
            dataToValidate.length<10 -> {
                ValidationResult.Failure("Please enter a valid mobile number.")
            }
            else                     -> {
                ValidationResult.Success
            }
        }
    }
}