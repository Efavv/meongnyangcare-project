package com.project.meongnyangcare2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class DiagnosisDetailFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 툴바 제목 설정
        (activity as? MainActivity)?.setToolbarTitle("진단 상세 정보")

        // 돌아가기 버튼 클릭 리스너 설정
        val btnBackToHome = view.findViewById<Button>(R.id.btnBackToHome)
        btnBackToHome.setOnClickListener {
            // PastDiagnosisFragment로 돌아가기
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout, PastDiagnosisFragment()) // fragment_container는 현재 프래그먼트가 들어가는 컨테이너의 ID
            fragmentTransaction.addToBackStack(null) // 뒤로가기를 할 수 있도록 스택에 추가
            fragmentTransaction.commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diagnosis_detail, container, false)
    }
}
