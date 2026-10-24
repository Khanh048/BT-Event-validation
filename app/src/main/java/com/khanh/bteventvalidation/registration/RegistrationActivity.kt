package com.khanh.bteventvalidation.registration

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.khanh.bteventvalidation.R
import com.khanh.bteventvalidation.databinding.ActivityRegistrationBinding
import com.khanh.bteventvalidation.validation.FormValidator

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupErrorClearing()
        binding.btnRegister.setOnClickListener { handleRegistration() }
    }

    private fun setupErrorClearing() = with(binding) {
        etFullName.doAfterTextChanged { tilFullName.error = null }
        etEmail.doAfterTextChanged { tilEmail.error = null }
        etPhone.doAfterTextChanged { tilPhone.error = null }
        etStudentId.doAfterTextChanged { tilStudentId.error = null }
        etClassName.doAfterTextChanged { tilClassName.error = null }
    }

    private fun handleRegistration() {
        if (!validateForm()) {
            Snackbar.make(binding.root, R.string.registration_invalid, Snackbar.LENGTH_SHORT).show()
            return
        }

        hideKeyboard()
        Snackbar.make(binding.root, R.string.registration_success, Snackbar.LENGTH_LONG).show()
    }

    private fun validateForm(): Boolean {
        val fullName = binding.etFullName.text?.toString()?.trim().orEmpty()
        val email = binding.etEmail.text?.toString()?.trim().orEmpty()
        val phone = binding.etPhone.text?.toString()?.trim().orEmpty()
        val studentId = binding.etStudentId.text?.toString()?.trim().orEmpty()
        val className = binding.etClassName.text?.toString()?.trim().orEmpty()

        var isFormValid = true
        var firstInvalidField: View? = null

        fun showError(inputLayout: TextInputLayout, errorMessage: String?) {
            inputLayout.error = errorMessage
            if (errorMessage != null) {
                isFormValid = false
                if (firstInvalidField == null) firstInvalidField = inputLayout.editText
            }
        }

        showError(
            binding.tilFullName,
            when {
                fullName.isBlank() -> getString(R.string.error_full_name_required)
                !FormValidator.isFullNameValid(fullName) -> getString(R.string.error_full_name_invalid)
                else -> null
            },
        )
        showError(
            binding.tilEmail,
            when {
                email.isBlank() -> getString(R.string.error_email_required)
                !FormValidator.isEmailValid(email) -> getString(R.string.error_email_invalid)
                else -> null
            },
        )
        showError(
            binding.tilPhone,
            when {
                phone.isBlank() -> getString(R.string.error_phone_required)
                !FormValidator.isPhoneValid(phone) -> getString(R.string.error_phone_invalid)
                else -> null
            },
        )
        showError(
            binding.tilStudentId,
            when {
                studentId.isBlank() -> getString(R.string.error_student_id_required)
                !FormValidator.isStudentIdValid(studentId) -> getString(R.string.error_student_id_invalid)
                else -> null
            },
        )
        showError(
            binding.tilClassName,
            when {
                className.isBlank() -> getString(R.string.error_class_name_required)
                !FormValidator.isClassNameValid(className) -> getString(R.string.error_class_name_invalid)
                else -> null
            },
        )

        firstInvalidField?.requestFocus()
        return isFormValid
    }

    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        currentFocus?.clearFocus()
    }
}
