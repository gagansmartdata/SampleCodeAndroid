package com.ggn.myapplication.di

import com.ggn.myapplication.base.validation.DataValidator
import com.ggn.myapplication.domain.usecases.validators.EmailValidator
import com.ggn.myapplication.domain.usecases.validators.MobileNumberValidator
import com.ggn.myapplication.domain.usecases.validators.NameValidator
import com.ggn.myapplication.domain.usecases.validators.PasswordValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class) //Can only be used in viewModels
object UtilsModule {

    @ViewModelScoped
    @EmailValidatorInject
    @Provides
    fun emailValidator(): DataValidator<String> {
        return EmailValidator()
    }
    @ViewModelScoped
    @PasswordValidatorInject
    @Provides
    fun passwordValidator(): DataValidator<String> {
        return PasswordValidator()
    }
    @ViewModelScoped
    @NamedValidatorInject
    @Provides
    fun nameValidator(): DataValidator<String> {
        return NameValidator()
    }
    @ViewModelScoped
    @MobileValidatorInject
    @Provides
    fun mobileValidator(): DataValidator<String> {
        return MobileNumberValidator()
    }
}