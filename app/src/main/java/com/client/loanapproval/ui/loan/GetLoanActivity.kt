package com.client.loanapproval.ui.loan

import android.app.ActivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.client.loanapproval.R


class GetLoanActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationController = findNavController(R.id.navigationHostFragment)

    }

    override fun onSupportNavigateUp() = navigationController.navigateUp()
}