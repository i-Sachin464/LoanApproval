package com.client.loanapproval.ui.introslider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.client.loanapproval.R
import com.client.loanapproval.ui.loan.GetLoanActivity
import kotlinx.android.synthetic.main.activity_introslider.*

class IntroSliderActivity : AppCompatActivity() {
    private val fragmentList = ArrayList<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // making the status bar transparent
        setContentView(R.layout.activity_introslider)
        val adapter = IntroSliderAdapter(this)
        vpIntroSlider.adapter = adapter
        fragmentList.addAll(
            listOf(
                IntroFragmentOne(), IntroFragmentTwo(), IntroFragmentThree()
            )
        )
        adapter.setFragmentList(fragmentList)
        indicatorLayout.setIndicatorCount(adapter.itemCount)
        indicatorLayout.selectCurrentPosition(0)
        registerListeners()
    }

    private fun registerListeners() {
        vpIntroSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                indicatorLayout.selectCurrentPosition(position)
               if (position < fragmentList.lastIndex) {
                   tvSkip.visibility = View.VISIBLE
                   indicatorLayout.visibility = View.VISIBLE
                   btn_start.visibility = View.GONE
               }
               else {
                   tvSkip.visibility = View.GONE
                   indicatorLayout.visibility = View.GONE
                   btn_start.visibility = View.VISIBLE
               }
            }
        })
        tvSkip.setOnClickListener {
            startActivity(Intent(this, GetLoanActivity::class.java))
            finish()
        }
        btn_start.setOnClickListener {
           val position = vpIntroSlider.currentItem
           if (position < fragmentList.lastIndex) {
               vpIntroSlider.currentItem = position + 1
           } else {
               startActivity(Intent(this, GetLoanActivity::class.java))
               finish()
           }
       }
    }
}