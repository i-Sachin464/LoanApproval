package com.client.loanapproval.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.client.loanapproval.ui.registerlogin.RegisterLoginActivity

/**
 * Activity for splash screen
 */
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@SplashScreenActivity, RegisterLoginActivity::class.java))
        finish()
    }
}