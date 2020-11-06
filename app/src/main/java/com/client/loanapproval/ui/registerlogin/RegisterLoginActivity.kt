package com.client.loanapproval.ui.registerlogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.client.loanapproval.R

class RegisterLoginActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_login)

        navigationController = findNavController(R.id.navigationLoginHostFragment)

    }

    override fun onSupportNavigateUp() = navigationController.navigateUp()
}