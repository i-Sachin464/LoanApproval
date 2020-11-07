package com.client.loanapproval.ui.kyc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.client.loanapproval.R

class PerformKYCActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perform_kyc)

        navigationController = findNavController(R.id.navigationKYCHostFragment)

    }

    override fun onSupportNavigateUp() = navigationController.navigateUp()
}