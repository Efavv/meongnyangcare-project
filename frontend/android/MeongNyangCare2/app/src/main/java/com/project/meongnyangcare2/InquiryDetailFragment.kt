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
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class InquiryDetailFragment : Fragment() {

    private lateinit var editInquiryTitle: EditText
    private lateinit var editInquiryContent: EditText
    private lateinit var btnCheck: Button
    private lateinit var btnDeleteInquiry: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.setToolbarTitle("상세 문의 내역")

        btnCheck = view.findViewById(R.id.btnCheck)
        btnDeleteInquiry = view.findViewById(R.id.btnDeleteInquiry)

        btnCheck.setOnClickListener {
            replaceFragment(InquiryFragment())
        }
        btnDeleteInquiry.setOnClickListener {
            showDeleteConfirmationDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_inquiry_detail, container, false)

        return view
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.frame_layout, fragment)
            addToBackStack(null)
        }
    }

    private fun showDeleteConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("문의내역 삭제")
            .setNegativeButton("취소", null)
            .setMessage("삭제하시겠습니까?")
            .setPositiveButton("삭제") {_, _ ->
                deleteInquiry()
            }
            .show()
    }
    private fun deleteInquiry() {
        AlertDialog.Builder(requireContext())
            .setTitle("문의내역 삭제")
            .setMessage("삭제가 완료되었습니다.")
            .setPositiveButton("확인") { _, _ ->
                replaceFragment(InquiryFragment())
            }
            .show()

    }


}