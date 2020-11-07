package com.client.loanapproval.ui.agreement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.client.loanapproval.R

class AgreementActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agreement)

        navigationController = findNavController(R.id.navigationAgreementHostFragment)

    }

    override fun onSupportNavigateUp() = navigationController.navigateUp()
}