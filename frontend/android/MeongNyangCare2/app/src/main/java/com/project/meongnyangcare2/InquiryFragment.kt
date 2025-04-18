package com.project.meongnyangcare2

import Inquiry
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InquiryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnInquiry: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.setToolbarTitle("문의 하기")

        recyclerView = view.findViewById(R.id.recyclerViewInquiry)
        btnInquiry = view.findViewById(R.id.btnInquiry)


        btnInquiry.setOnClickListener {
            replaceFragment(InquiryFormFragment())
        }

        val inquiryList = listOf(
            Inquiry("1", "앱 오류 관련 문의", "답변 완료"),
            Inquiry("2", "반려동물 등록 방법", "답변 완료"),
            Inquiry("3", "기타 문의", "답변 대기")
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = InquiryAdapter(inquiryList) { inquiry ->
            openNextFragment(inquiry)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inquiry, container, false)

    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.frame_layout, fragment)
            addToBackStack(null)
        }
    }

    private fun openNextFragment(inquiry: Inquiry) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, InquiryDetailFragment())
            .addToBackStack(null)
            .commit()
    }

}