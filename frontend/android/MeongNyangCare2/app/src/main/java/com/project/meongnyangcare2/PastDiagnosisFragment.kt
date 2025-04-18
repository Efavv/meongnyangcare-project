package com.project.meongnyangcare2

import Diagnosis
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PastDiagnosisFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 툴바 제목 설정
        (activity as? MainActivity)?.setToolbarTitle("과거 진단 기록")

        // RecyclerView 초기화
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewDiagnosis)

        // 목업 데이터 추가
        val diagnosisList = listOf(
            Diagnosis("1", R.drawable.test_dog, "코코", "여드름", "중기", "6"),
            Diagnosis("2", R.drawable.test_cat, "미미", "여드름", "초기", "2")
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = DiagnosisAdapter(diagnosisList) { diagnosis ->
            openNextFragment(diagnosis)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_past_diagnosis, container, false)
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.frame_layout, fragment)
            addToBackStack(null)
        }
    }

    private fun openNextFragment(diagnosis: Diagnosis) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, DiagnosisDetailFragment())
            .addToBackStack(null)
            .commit()
    }
}
