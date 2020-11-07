package com.client.loanapproval.ui.registerlogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.client.loanapproval.R
import kotlinx.android.synthetic.main.fragment_register.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signin.setOnClickListener {
            if (checkValidation())
                findNavController().navigate(R.id.action_registerFragment_to_signinFragment)
        }
        btn_register.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_verifyFragment)
        }
    }

    private fun checkValidation(): Boolean {
        if (first_name.text.isNullOrEmpty()) {
            input_fname.error = "Enter First name"
            return false
        }
        if (last_name.text.isNullOrEmpty()) {
            input_lname.error = "Enter Last name"
            return false
        }
        if (username.text.isNullOrEmpty()) {
            input_username.error = "Enter username"
            return false
        }
        if (phone_no.text.isNullOrEmpty()) {
            input_phone.error = "Enter Phone number"
            return false
        }
        if (phone_no.getText().toString().length <= 10) {
            input_phone.error = "Enter valid Phone number"
            return false
        }
        if (email.text.isNullOrEmpty()) {
            input_email.error = "Please enter email."
            return false
        }
        if (password.text.isNullOrEmpty()) {
            input_password.error = "Please enter password"
            return false
        }
        if (re_password.text.isNullOrEmpty()) {
            input_re_password.error = "Please enter re-password."
            return false
        }
        if (password.text.toString() == re_password.text.toString()) {
            input_re_password.error = "Password didn't match"
            return false
        }
        return true
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}