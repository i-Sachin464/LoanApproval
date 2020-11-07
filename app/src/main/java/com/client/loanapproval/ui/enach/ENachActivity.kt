package com.client.loanapproval.ui.enach

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.client.loanapproval.R

class ENachActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enach)

        navigationController = findNavController(R.id.navigationEnachHostFragment)

    }

    override fun onSupportNavigateUp() = navigationController.navigateUp()
}