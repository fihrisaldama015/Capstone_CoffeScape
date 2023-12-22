package com.lutfi.coffeescape.ui.landingpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.lutfi.coffeescape.R
import com.lutfi.coffeescape.databinding.ActivityWelcomeBinding
import com.lutfi.coffeescape.ui.login.LoginActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    private var currentMenu = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
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
            startActivity(Intent(this, com.lutfi.coffeescape.ui.register.RegisterActivity::class.java))
        }

        binding.nextButton.setOnClickListener {
            currentMenu += 1
            setMenu()
        }

        binding.previousButton.setOnClickListener {
            currentMenu -= 1
            setMenu()
        }
    }

    private fun setMenu() {
        when (currentMenu) {
            1 -> {
                binding.motionLayout.transitionToState(R.id.menu1)
                binding.menu1.setImageResource(R.drawable.baseline_circle_24)
                binding.menu2.setImageResource(R.drawable.grey_circle_24)
                binding.menu3.setImageResource(R.drawable.grey_circle_24)
                binding.menu4.setImageResource(R.drawable.grey_circle_24)
                binding.previousButton.alpha = 0f
                binding.nextButton.alpha = 1f
            }
            2 -> {
                binding.motionLayout.transitionToState(R.id.menu2)
                binding.menu1.setImageResource(R.drawable.grey_circle_24)
                binding.menu2.setImageResource(R.drawable.baseline_circle_24)
                binding.menu3.setImageResource(R.drawable.grey_circle_24)
                binding.menu4.setImageResource(R.drawable.grey_circle_24)
                binding.previousButton.alpha = 1f
                binding.nextButton.alpha = 1f
            }
            3 -> {
                binding.motionLayout.transitionToState(R.id.menu3)
                binding.menu1.setImageResource(R.drawable.grey_circle_24)
                binding.menu2.setImageResource(R.drawable.grey_circle_24)
                binding.menu3.setImageResource(R.drawable.baseline_circle_24)
                binding.menu4.setImageResource(R.drawable.grey_circle_24)
                binding.previousButton.alpha = 1f
                binding.nextButton.alpha = 1f
            }
            4 -> {
                binding.motionLayout.transitionToState(R.id.menu4)
                binding.menu1.setImageResource(R.drawable.grey_circle_24)
                binding.menu2.setImageResource(R.drawable.grey_circle_24)
                binding.menu3.setImageResource(R.drawable.grey_circle_24)
                binding.menu4.setImageResource(R.drawable.baseline_circle_24)
                binding.previousButton.alpha = 1f
                binding.nextButton.alpha = 0f
            }
            else -> {
                currentMenu = if (currentMenu < 1) 1 else 4
            }
        }
    }
}