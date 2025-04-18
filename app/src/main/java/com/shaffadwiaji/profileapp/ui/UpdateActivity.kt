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
import com.shaffadwiaji.profileapp.data.User
import com.shaffadwiaji.profileapp.databinding.ActivityUpdateBinding
import com.shaffadwiaji.profileapp.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private val userViewModel: UserViewModel by viewModels()
    private var currentUser: User? = null
    private var userEmail: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userEmail = intent.getStringExtra("email")

        userEmail?.let { email ->
            lifecycleScope.launch {
                val user = userViewModel.getUserByEmail(email)
                user?.let {
                    currentUser = it
                    // Tampilkan data ke form
                    binding.etName.setText(it.name)
                    binding.etPassword.setText(it.password)
                    binding.etPhone.setText(it.phone)
                    binding.etAddress.setText(it.address)
                }
            }
        }

        binding.btnUpdate.setOnClickListener {
            updateUser()
        }
    }

    private fun updateUser() {
        val name = binding.etName.text.toString()
        val password = binding.etPassword.text.toString()
        val phone = binding.etPhone.text.toString()
        val address = binding.etAddress.text.toString()

        if (name.isEmpty() || password.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            return
        }

        val updatedUser = currentUser?.copy(
            name = name,
            password = password,
            phone = phone,
            address = address
        )

        updatedUser?.let {
            userViewModel.updateUser(it)
            Toast.makeText(this, "Profile berhasil diupdate", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("email", it.email) // kirim email kembali ke Home
            startActivity(intent)
            finish()
        }

    }
}