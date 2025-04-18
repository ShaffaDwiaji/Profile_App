package com.shaffadwiaji.profileapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.shaffadwiaji.profileapp.ui.LoginActivity

class MainActivity : AppCompatActivity() {

    // Waktu tampil splash (misal 2 detik)
    private val splashTime: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, splashTime)
    }
}