package com.project.meongnyangcare2

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.replace

class InquiryFormFragment : Fragment() {

    private lateinit var editInquiryTitle: EditText
    private lateinit var editInquiryContent: EditText
    private lateinit var errorInquiryTitle: TextView
    private lateinit var errorInquiryContent: TextView
    private lateinit var btnSubmitInquiry: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.setToolbarTitle("문의 하기")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_inquiry_form, container, false)

        editInquiryTitle = view.findViewById(R.id.editInquiryTitle)
        editInquiryContent = view.findViewById(R.id.editInquiryContent)
        errorInquiryTitle = view.findViewById(R.id.errorInquiryTitle)
        errorInquiryContent = view.findViewById(R.id.errorInquiryContent)
        btnSubmitInquiry = view.findViewById(R.id.btnSubmitInquiry)

        btnSubmitInquiry.setOnClickListener {
            submitInquiry()
        }
        return view
    }

    private fun submitInquiry() {
        var isValid = true

        if(editInquiryTitle.text.isEmpty()) {
            errorInquiryTitle.visibility = View.VISIBLE
            isValid = false
        } else {
            errorInquiryTitle.visibility = View.GONE
        }

        if(editInquiryContent.text.isEmpty()) {
            errorInquiryContent.visibility = View.VISIBLE
            isValid = false
        } else {
            errorInquiryContent.visibility = View.GONE
        }

        if (!isValid) return
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("제출 완료")
        builder.setMessage("문의가 제출되었습니다")
        builder.setPositiveButton("확인") { _, _ ->
            val fragment = InquiryFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit()
        }
        builder.show()
    }

}