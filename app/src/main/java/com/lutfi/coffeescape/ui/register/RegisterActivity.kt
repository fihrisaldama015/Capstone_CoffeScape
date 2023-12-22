package com.lutfi.coffeescape.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import android.widget.Toast
import com.lutfi.coffeescape.R
import com.lutfi.coffeescape.databinding.ActivityRegisterBinding
import com.lutfi.coffeescape.ui.ViewModelFactory
import com.lutfi.coffeescape.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        viewModel.isSuccess.observe(this){
            showAlert(it)
        }

        registerUser()
    }

    private fun registerUser() {
        binding.registerButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val confirmPassword = binding.confirmPasswordEditText.text.toString()

            val error = binding.passwordEditText.error
            if (password == confirmPassword && error == null) {
                showLoading(true)
                viewModel.registerUser(name, email, password)
            } else if (confirmPassword == "") {
                showToast(getString(R.string.confirm))
            } else if(error != null) {
                showToast(error.toString())
            } else {
                showToast(getString(R.string.confirm_not_same))
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showAlert(isSuccess: Boolean) {
        viewModel.messages.observe(this){ response ->
            if (isSuccess) {
                showToast(getString(R.string.register_success))
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            } else {
                AlertDialog.Builder(this).apply {
                    setTitle(R.string.register_fail)
                    setMessage(response)
                    setNegativeButton(R.string.back) { dialog, _ ->
                        dialog.cancel()
                    }
                    create()
                    show()
                }
            }
        }
    }

    private fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}