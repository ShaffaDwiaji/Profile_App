package com.shaffadwiaji.profileapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.shaffadwiaji.profileapp.R
import com.shaffadwiaji.profileapp.data.User
import com.shaffadwiaji.profileapp.databinding.ActivityRegisterBinding
import com.shaffadwiaji.profileapp.viewmodel.UserViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val name = binding.etName.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val phone = binding.etPhone.text.toString()
        val address = binding.etAddress.text.toString()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            return
        }

        val user = User(
            name = name,
            email = email,
            password = password,
            phone = phone,
            address = address
        )

        // Simpan user ke database
        userViewModel.register(user)

        Toast.makeText(this, "Registrasi berhasil", Toast.LENGTH_SHORT).show()

        // Pindah ke Login
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}