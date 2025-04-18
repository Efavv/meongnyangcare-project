package com.project.meongnyangcare2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView

class AnalysisResultFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.setToolbarTitle("피부질환 진단 결과")

        // 병원 이미지 클릭 시 다이얼로그 표시
        view.findViewById<ImageView>(R.id.hospitalImageView).setOnClickListener {
            val dialog = NearbyVetHospitalFragment()
            dialog.show(parentFragmentManager, "HospitalDialog")
        }

        // 홈으로 돌아가기 버튼 클릭 시 HomeFragment로 이동
        view.findViewById<Button>(R.id.btnBackToHome).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, HomeFragment()) // 올바른 컨테이너 ID 사용
                .addToBackStack(null) // 뒤로 가기 기능 지원
                .commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analysis_result, container, false)
    }
}
