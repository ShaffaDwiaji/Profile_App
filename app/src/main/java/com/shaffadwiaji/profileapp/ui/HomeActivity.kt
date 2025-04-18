package com.shaffadwiaji.profileapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.shaffadwiaji.profileapp.R
import com.shaffadwiaji.profileapp.databinding.ActivityHomeBinding
import com.shaffadwiaji.profileapp.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val userViewModel: UserViewModel by viewModels()
    private var userEmail: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil email dari intent
        userEmail = intent.getStringExtra("email")

        // Tampilkan data user
        userEmail?.let { email ->
            lifecycleScope.launch {
                val user = userViewModel.getUserByEmail(email)
                user?.let {
                    binding.tvName.text = it.name
                    binding.tvEmail.text = it.email
                    binding.tvPhone.text = it.phone
                    binding.tvAddress.text = it.address
                }
            }
        }

        // Navigasi ke update profile
        binding.btnUpdateProfile.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java).apply {
                putExtra("email", userEmail)
            }
            startActivity(intent)
        }
    }
}