package com.client.loanapproval.ui.loan

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.client.loanapproval.R
import kotlinx.android.synthetic.main.fragment_upload_document.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UploadDocumentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class UploadDocumentFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_upload_document, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        choose_pan.setOnClickListener {
            openFileManager()
        }
        day.setOnClickListener {
            requireActivity().showDialog(1)
        }
        btn_next.setOnClickListener {
            if (checkValidation()) {
                findNavController().navigate(R.id.action_upploadDocumentFragment_to_loanAmountFragment)
            }
        }
    }

    private fun checkValidation(): Boolean {
        if (phone_no.text.isNullOrEmpty()) {
            input_phone_no.error = "Enter Phone number"
            return false
        }
        if (email.text.isNullOrEmpty()) {
            input_email.error = "Enter Email"
            return false
        }
        if (phone_no.getText()!!.length <= 10) {
            input_phone_no.error = "Enter valid Phone number"
            return false
        }

        return true
    }

    private fun openFileManager() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        try {
            startActivityForResult(
                Intent.createChooser(intent, "Select a File to Upload"),
                2
            )
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                requireContext(), "Please install a File Manager.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private lateinit var mDatePicker: DatePickerDialog
    fun onCreateDialog(id: Int): Dialog {
        mDatePicker = DatePickerDialog(
            requireContext(),
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
            year.setText(i)
            month.setText(j)
            day.setText(k)
            requireActivity().removeDialog(1)
        }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UpploadDocumentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UploadDocumentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}