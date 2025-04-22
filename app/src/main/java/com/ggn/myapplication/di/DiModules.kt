package com.ggn.myapplication.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class EmailValidatorInject

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class PasswordValidatorInject

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NamedValidatorInject

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MobileValidatorInject