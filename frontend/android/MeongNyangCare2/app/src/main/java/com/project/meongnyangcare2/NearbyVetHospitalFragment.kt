package com.project.meongnyangcare2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.DialogFragment

class NearbyVetHospitalFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_hospital_image, container, false)

        val imageView = view.findViewById<ImageView>(R.id.dialogImageView)
        val closeButton = view.findViewById<ImageButton>(R.id.closeButton)

        // 이미지 설정 (arguments에서 전달된 이미지 사용)
        arguments?.getInt("imageResId")?.let { imageView.setImageResource(it) }

        // 닫기 버튼 클릭 시 다이얼로그 닫기
        closeButton.setOnClickListener {
            dismiss()
        }

        return view
    }

    companion object {
        fun newInstance(imageResId: Int): NearbyVetHospitalFragment {
            val fragment = NearbyVetHospitalFragment()
            val args = Bundle()
            args.putInt("imageResId", imageResId)
            fragment.arguments = args
            return fragment
        }
    }
}