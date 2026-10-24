package com.khanh.bteventvalidation.validation

import android.util.Patterns

object FormValidator {
    private val vietnamesePhoneRegex = Regex("^(03|05|07|08|09)\\d{8}$")
    private val studentIdRegex = Regex("^[A-Za-z0-9]{5,15}$")
    private val classNameRegex = Regex("^[\\p{L}\\d]+(?:[ ._-][\\p{L}\\d]+)*$")

    fun isFullNameValid(value: String): Boolean {
        val normalizedName = value.trim()
        val words = normalizedName.split(Regex("\\s+")).filter(String::isNotBlank)
        val containsOnlyNameCharacters = normalizedName.all { character ->
            character.isLetter() || character.isWhitespace() || character in ".'-"
        }

        return words.size >= 2 && containsOnlyNameCharacters
    }

    fun isEmailValid(value: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(value.trim()).matches()

    fun isPhoneValid(value: String): Boolean =
        vietnamesePhoneRegex.matches(value.trim())

    fun isStudentIdValid(value: String): Boolean =
        studentIdRegex.matches(value.trim())

    fun isClassNameValid(value: String): Boolean {
        val normalizedClassName = value.trim()
        return normalizedClassName.length in 2..30 && classNameRegex.matches(normalizedClassName)
    }
}
