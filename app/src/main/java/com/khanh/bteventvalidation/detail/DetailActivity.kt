package com.khanh.bteventvalidation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khanh.bteventvalidation.databinding.ActivityDetailBinding
import com.khanh.bteventvalidation.model.StudentRegistration
import com.khanh.bteventvalidation.registration.RegistrationActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showRegistrationDetails()
        binding.btnBackToRegistration.setOnClickListener { openRegistrationScreen() }
    }

    private fun showRegistrationDetails() = with(binding) {
        tvFullNameValue.text = intent.getStringExtra(EXTRA_FULL_NAME).orEmpty()
        tvEmailValue.text = intent.getStringExtra(EXTRA_EMAIL).orEmpty()
        tvPhoneValue.text = intent.getStringExtra(EXTRA_PHONE).orEmpty()
        tvStudentIdValue.text = intent.getStringExtra(EXTRA_STUDENT_ID).orEmpty()
        tvClassNameValue.text = intent.getStringExtra(EXTRA_CLASS_NAME).orEmpty()
    }

    private fun openRegistrationScreen() {
        val registrationIntent = Intent(this, RegistrationActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        startActivity(registrationIntent)
        finish()
    }

    companion object {
        private const val EXTRA_FULL_NAME = "extra_full_name"
        private const val EXTRA_EMAIL = "extra_email"
        private const val EXTRA_PHONE = "extra_phone"
        private const val EXTRA_STUDENT_ID = "extra_student_id"
        private const val EXTRA_CLASS_NAME = "extra_class_name"

        fun createIntent(context: Context, registration: StudentRegistration): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_FULL_NAME, registration.fullName)
                putExtra(EXTRA_EMAIL, registration.email)
                putExtra(EXTRA_PHONE, registration.phone)
                putExtra(EXTRA_STUDENT_ID, registration.studentId)
                putExtra(EXTRA_CLASS_NAME, registration.className)
            }
    }
}
