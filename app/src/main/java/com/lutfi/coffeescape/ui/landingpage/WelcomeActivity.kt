package com.lutfi.coffeescape.ui.landingpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lutfi.coffeescape.R
import com.lutfi.coffeescape.databinding.ActivityWelcomeBinding
import com.lutfi.coffeescape.ui.login.LoginActivity
import com.lutfi.coffeescape.ui.register.RegisterActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        setupAction()
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.createAccountButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}