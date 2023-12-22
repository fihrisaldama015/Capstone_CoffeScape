package com.lutfi.coffeescape.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.lutfi.coffeescape.MainActivity
import com.lutfi.coffeescape.R
import com.lutfi.coffeescape.data.pref.UserModel
import com.lutfi.coffeescape.databinding.ActivityLoginBinding
import com.lutfi.coffeescape.databinding.ActivityWelcomeBinding
import com.lutfi.coffeescape.ui.ViewModelFactory
import com.lutfi.coffeescape.ui.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        viewModel.isLoading.observe(this){
            showLoading(it)
        }

        viewModel.isSuccess.observe(this) {
            showAlert(it)
        }

        loginUser()
    }

    private fun loginUser() {
        binding.logInButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            viewModel.login(email, password)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showAlert(isSuccess: Boolean) {
        viewModel.messages.observe(this){ response ->
            val email = binding.emailEditText.text.toString()
            viewModel.dataLogin.observe(this) { data ->
                viewModel.saveSession(UserModel(email, data.userId, data.token))
            }

            if (isSuccess) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            } else {
                AlertDialog.Builder(this).apply {
                    setTitle(R.string.login_fail)
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
}