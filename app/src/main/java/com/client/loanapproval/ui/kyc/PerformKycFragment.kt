package com.client.loanapproval.ui.kyc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.client.loanapproval.R
import com.client.loanapproval.util.IdType
import com.client.loanapproval.util.Util
import kotlinx.android.synthetic.main.fragment_perform_kyc.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PerformKycFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PerformKycFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_perform_kyc, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_submit.setOnClickListener {
            if (checkValidation())
                findNavController().navigate(R.id.action_performKycFragment_to_thankyouFragment)
        }
    }

    private fun checkValidation(): Boolean {
        if (pancard.text.isNullOrEmpty()) {
            input_pan.error = "Enter valid pan number"
            return false
        }
        if (aadhar.text.isNullOrEmpty()) {
            input_aadhar.error = "Enter aadhar number"

            return false
        }
        var util = Util()
        if (!util.validateIdCard(IdType.PAN_CARD.text, pancard.text.toString().toUpperCase())) {
            input_pan.error = "Enter valid pan number"
            Toast.makeText(requireContext(), "Enter a valid pan card number", Toast.LENGTH_SHORT)
                .show()

            return false
        }
        if (!util.validateIdCard(IdType.AADHAR_CARD.text, aadhar.text.toString().toUpperCase())) {
            input_aadhar.error = "Enter valid aadhar number"

            Toast.makeText(requireContext(), "Enter a valid aadhar card number", Toast.LENGTH_SHORT)
                .show()
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
         * @return A new instance of fragment PerformKycFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PerformKycFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}