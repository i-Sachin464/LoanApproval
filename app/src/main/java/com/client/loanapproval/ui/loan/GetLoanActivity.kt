package com.client.loanapproval.ui.loan

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.client.loanapproval.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class GetLoanActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController

    var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationController = findNavController(R.id.navigationHostFragment)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.option_home -> {
                    if (flag) {
                        navigationController.navigate(R.id.homeFragment)
                        true
                    } else
                        Toast.makeText(
                            this,
                            "First you can get a loan approval.",
                            Toast.LENGTH_SHORT
                        ).show()
                }
                R.id.option_get_loan -> {
                    navigationController.navigate(R.id.getLoanFragment)
                    true
                }
            }
            false
        }
    }

    private lateinit var mDatePicker: DatePickerDialog
    override fun onCreateDialog(id: Int): Dialog {
        mDatePicker = DatePickerDialog(
            this,
            myDateSetListener,
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
        mDatePicker.datePicker.maxDate = System.currentTimeMillis()
        return mDatePicker
    }

    private var myDateSetListener: DatePickerDialog.OnDateSetListener =
        DatePickerDialog.OnDateSetListener { _, i, j, k ->
            this.removeDialog(1)
        }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val bundle: Bundle? = intent!!.extras
        if (bundle != null) {
            val message = bundle.getString("goto")
            if (message == "home") {
                flag = true
                setSelectItemID(R.id.option_home)
            } else {
                setSelectItemID(R.id.option_get_loan)
            }
        }
    }

    private fun setSelectItemID(id: Int) {
        bottom_navigation.selectedItemId = id

    }

    override fun onSupportNavigateUp() = navigationController.navigateUp()
}