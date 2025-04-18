package com.project.meongnyangcare2

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class UploadPetImageFragment : Fragment() {

    private lateinit var ivUploadImage: ImageView

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val REQUEST_GALLERY_PICK = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upload_pet_image, container, false)

        ivUploadImage = view.findViewById(R.id.ivUploadImage)
        val btnAnalyze = view.findViewById<Button>(R.id.btnAnalyze)

        // 이미지뷰 클릭 시 다이얼로그 표시
        ivUploadImage.setOnClickListener {
            showImagePickerDialog()
        }

        // 분석 버튼 클릭 시
        btnAnalyze.setOnClickListener {
            // 분석 진행 중 다이얼로그 표시
            showAnalysisDialog()
        }

        return view
    }

    private fun showImagePickerDialog() {
        val options = arrayOf("사진 촬영", "갤러리에서 선택", "취소")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("이미지 업로드 방법 선택")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> openCamera()
                1 -> openGallery()
                2 -> dialog.dismiss()
            }
        }
        builder.show()
    }

    // 카메라 실행
    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
    }

    // 갤러리 열기
    private fun openGallery() {
        val pickPhotoIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhotoIntent, REQUEST_GALLERY_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    ivUploadImage.setImageBitmap(imageBitmap)
                }
                REQUEST_GALLERY_PICK -> {
                    val imageUri: Uri? = data?.data
                    ivUploadImage.setImageURI(imageUri)
                }
            }
        }
    }

    // 분석 중 다이얼로그 표시
    private fun showAnalysisDialog() {
        // AlertDialog 생성
        val builder = AlertDialog.Builder(requireContext())
        builder.setCancelable(false) // 뒤로 가기 방지
        builder.setView(R.layout.activity_dialong_analysis_progress) // 분석 중 레이아웃

        // AlertDialog 생성
        val dialog = builder.create()
        dialog.show()

        // 진행 중 애니메이션 추가
        val dialogProgressBar = dialog.findViewById<ProgressBar>(R.id.dialogProgressBar)
        val dialogMessage = dialog.findViewById<TextView>(R.id.dialogMessage)

        // "분석 중" 애니메이션 시작
        dialogProgressBar?.visibility = View.VISIBLE
        dialogMessage?.text = "진단 중입니다. 잠시만 기다려주세요."

        // 예시로 3초 뒤에 팝업을 닫고 분석 완료 메시지를 표시
        dialogProgressBar?.postDelayed({
            // 분석 완료 후 팝업 닫기
            dialog.dismiss()

            // AnalysisResultFragment로 이동
            val analysisResultFragment = AnalysisResultFragment()

            // FragmentTransaction을 통해 AnalysisResultFragment로 교체
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, analysisResultFragment)  // 'frame_layout'을 분석 결과 화면이 들어갈 컨테이너로 사용
                .addToBackStack(null)  // 뒤로 가기 스택에 추가
                .commit()  // 트랜잭션 커밋
        }, 3000) // 3초 후에 팝업 닫기
    }
}
