package com.client.loanapproval.ui.introslider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.client.loanapproval.R

class IntroFragmentOne : Fragment() {
   override fun onCreateView(
       inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
   ): View? {
       return inflater.inflate(R.layout.fragment_intro_one, container, false)
   }
}