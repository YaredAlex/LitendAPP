package com.example.litendapp.ui.login

/**
 * Data validation state of the login form.
 */
data class oginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)