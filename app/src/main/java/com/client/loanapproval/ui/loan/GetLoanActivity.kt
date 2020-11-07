package com.client.loanapproval.ui.loan

import android.app.ActivityManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.client.loanapproval.R
import kotlinx.android.synthetic.main.activity_main.*


class GetLoanActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationController = findNavController(R.id.navigationHostFragment)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.option_home -> {
                    navigationController.navigate(R.id.homeFragment)
                    true
                }
                R.id.option_get_loan -> {
                    navigationController.navigate(R.id.getLoanFragment)
                    true
                }
            }
            false
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val bundle: Bundle? = intent!!.extras
        if (bundle != null) {
            val message = bundle.getString("goto")
            if (message == "home") {
                setSelectItemID(R.id.option_home)
            } else {
                setSelectItemID(R.id.option_get_loan)
            }
        }
    }

    fun setSelectItemID(id: Int) {
        bottom_navigation.selectedItemId = id

    }

    override fun onSupportNavigateUp() = navigationController.navigateUp()
}