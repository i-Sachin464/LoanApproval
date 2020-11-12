package com.client.loanapproval.ui.registerlogin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.client.loanapproval.R
import com.client.loanapproval.ui.introslider.IntroSliderActivity
import kotlinx.android.synthetic.main.fragment_verify.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VerifyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VerifyFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_verify, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillCode(pin_first_edittext)
        fillCode(pin_second_edittext)
        fillCode(pin_third_edittext)
        fillCode(pin_forth_edittext)
        fillCode(pin_fifth_edittext)
        fillCode(pin_sixth_edittext)
        btn_verify.setOnClickListener {
            if (pinVerification())
                startActivity(Intent(requireActivity(), IntroSliderActivity::class.java))
        }

    }

    private fun pinVerification(): Boolean {
        var pin: String = "123456"
        if (!(pin_first_edittext.text.isNullOrEmpty() &&
            pin_second_edittext.text.isNullOrEmpty() &&
            pin_third_edittext.text.isNullOrEmpty() &&
            pin_forth_edittext.text.isNullOrEmpty() &&
            pin_fifth_edittext.text.isNullOrEmpty() &&
            pin_sixth_edittext.text.isNullOrEmpty())
        ) {
            if (pin_first_edittext.text.toString().equals(pin[0] + "", false) &&
                pin_second_edittext.text.toString().equals(pin[1] + "", false) &&
                pin_third_edittext.text.toString().equals(pin[2] + "", false) &&
                pin_forth_edittext.text.toString().equals(pin[3] + "", false) &&
                pin_fifth_edittext.text.toString().equals(pin[4] + "", false) &&
                pin_sixth_edittext.text.toString().equals(pin[5] + "", false)
                    )
                return true
        }
        return false
    }

    private fun fillCode(editText: EditText) {
        editText.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (pin_first_edittext.text!!.length == 1) {
                pin_second_edittext.requestFocus()
            }
            if (pin_second_edittext.text!!.length == 1) {
                pin_third_edittext.requestFocus()
            }
            if (pin_third_edittext.text!!.length == 1) {
                pin_forth_edittext.requestFocus()
            }
            if (pin_forth_edittext.text!!.length == 1) {
                pin_fifth_edittext.requestFocus()
            }
            if (pin_fifth_edittext.text!!.length == 1) {
                pin_sixth_edittext.requestFocus()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VerifyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VerifyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}