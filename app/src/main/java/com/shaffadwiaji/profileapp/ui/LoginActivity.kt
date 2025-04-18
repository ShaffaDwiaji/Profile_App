package com.shaffadwiaji.profileapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.shaffadwiaji.profileapp.R
import com.shaffadwiaji.profileapp.databinding.ActivityLoginBinding
import com.shaffadwiaji.profileapp.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        binding.tvToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun loginUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email dan Password wajib diisi", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            val user = userViewModel.login(email, password)

            if (user != null) {
                Toast.makeText(this@LoginActivity, "Login berhasil", Toast.LENGTH_SHORT).show()

                // Kirim data user ke HomeActivity
                val intent = Intent(this@LoginActivity, HomeActivity::class.java).apply {
                    putExtra("email", user.email)
                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this@LoginActivity, "Email atau Password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}