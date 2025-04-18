package com.project.meongnyangcare2

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.replace

class AddPetFragment : Fragment() {

    private lateinit var ivAddPetImage: ImageView
    private lateinit var editPetName: EditText
    private lateinit var editPetAge: EditText
    private lateinit var editPetBreed: EditText
    private lateinit var errorPetName: TextView
    private lateinit var errorPetAge: TextView
    private lateinit var errorPetBreed: TextView
    private lateinit var radioPetType: RadioGroup
    private lateinit var radioPetGender: RadioGroup

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val REQUEST_GALLERY_PICK = 2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.setToolbarTitle("반려동물 추가")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_pet, container, false)

        ivAddPetImage = view.findViewById(R.id.ivAddPet)
        editPetName = view.findViewById(R.id.editPetName)
        editPetAge = view.findViewById(R.id.editPetAge)
        editPetBreed = view.findViewById(R.id.editPetBreed)
        errorPetName = view.findViewById(R.id.errorPetName)
        errorPetAge = view.findViewById(R.id.errorPetAge)
        errorPetBreed = view.findViewById(R.id.errorPetBreed)
        radioPetType = view.findViewById(R.id.radioPetType)
        radioPetGender = view.findViewById(R.id.radioPetGender)

        ivAddPetImage.setOnClickListener {
            showImagePickerDialog()
        }

        view.findViewById<View>(R.id.btnSaveUser).setOnClickListener {
            savePetDetails()
        }

        return view
    }

    private fun showImagePickerDialog() {
        val options = arrayOf("사진 촬영", "갤러리에서 선택", "기본 이미지 사용")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("이미지 업로드 방법 선택")
        builder.setItems(options) { dialog, which ->
            when(which) {
                0 -> openCamera()
                1 -> openGallery()
                2 -> dialog.dismiss()
            }
        }
        builder.show()
    }

    private fun openCamera() {
        val takePicktureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePicktureIntent, REQUEST_IMAGE_CAPTURE)
    }

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
                    ivAddPetImage.setImageBitmap(imageBitmap)
                }

                REQUEST_GALLERY_PICK -> {
                    val imageUri: Uri? = data?.data
                    ivAddPetImage.setImageURI(imageUri)
                }
            }
        }
    }

    private fun savePetDetails() {
        var isValid = true

        if(editPetName.text.isEmpty()) {
            errorPetName.visibility = View.VISIBLE
            isValid = false
        } else {
            errorPetName.visibility = View.GONE
        }

        if(editPetAge.text.isEmpty()) {
            errorPetAge.visibility = View.VISIBLE
            isValid = false
        } else {
            errorPetAge.visibility = View.GONE
        }

        if(editPetBreed.text.isEmpty()) {
            errorPetBreed.visibility = View.VISIBLE
            isValid = false
        } else {
            errorPetBreed.visibility = View.GONE
        }

        if (!isValid) return

        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("저장되었습니다.")
        builder.setPositiveButton("확인") {_, _ ->
            val fragment = MyPageFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit()
        }
        builder.show()
    }
}