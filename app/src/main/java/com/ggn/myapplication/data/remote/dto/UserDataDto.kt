package com.ggn.myapplication.data.remote.dto

import com.ggn.myapplication.domain.model.UserData

data class UserDataDto(
    val accountVerificationToken: String,
    val createdAt: String,
    val email: String,
    val firstName: String,
    val id: String,
    val isAccountLocked: Boolean,
    val isBlocked: Boolean,
    val isDeleted: Boolean,
    val isEmailVerified: Boolean,
    val lastName: String,
    val mobile: String,
    val password: String,
    val roleId: String,
    val status: Boolean,
    val updatedAt: String
) {
    fun toUserData(): UserData {
        return UserData(
            accountVerificationToken = accountVerificationToken,
            email = email,
            firstName = firstName,
            id = id,
            isAccountLocked = isAccountLocked,
            isEmailVerified = isEmailVerified,
            lastName = lastName,
            mobile = mobile,
        )
    }
}
