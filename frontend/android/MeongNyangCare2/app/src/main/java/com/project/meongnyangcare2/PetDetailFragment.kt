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
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class PetDetailFragment : Fragment() {

    private lateinit var btnUpdatePet: Button
    private lateinit var btnDeletePet: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.setToolbarTitle("반려동물 정보")

        btnUpdatePet = view.findViewById(R.id.btnUpdatePet)
        btnDeletePet = view.findViewById(R.id.btnDeletePet)

        btnUpdatePet.setOnClickListener {
            replaceFragment(UpdatePetFragment())
        }

        btnDeletePet.setOnClickListener {
            showDeleteConfirmationDialog()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pet_detail, container, false)

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
            .setTitle("반려동물 삭제")
            .setNegativeButton("취소", null)
            .setMessage("정말로 삭제하시겠습니까?")
            .setPositiveButton("삭제") {_, _ ->
                deletePet()
            }
            .show()
    }

    private fun deletePet() {
        AlertDialog.Builder(requireContext())
            .setTitle("반려동물 삭제")
            .setMessage("삭제가 완료되었습니다.")
            .setPositiveButton("확인") { _, _ ->
                replaceFragment(MyPageFragment())
            }
            .show()

    }

}