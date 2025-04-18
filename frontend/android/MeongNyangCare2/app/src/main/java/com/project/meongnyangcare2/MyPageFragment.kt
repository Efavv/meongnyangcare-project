package com.project.meongnyangcare2

import Pet
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.meongnyangcare.StartActivity

class MyPageFragment : Fragment() {

    private lateinit var btnUpdateUser: Button
    private lateinit var btnAddPet: Button
    private lateinit var btnInquiry: Button
    private lateinit var btnLogOut: Button
    private lateinit var btnWithdrawMember: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)


        (activity as? MainActivity)?.setToolbarTitle("마이페이지")

        btnUpdateUser = view.findViewById(R.id.btnUpdateUser)
        btnAddPet = view.findViewById(R.id.btnAddPet)
        btnInquiry = view.findViewById(R.id.btnInquiry)
        btnLogOut = view.findViewById(R.id.btnLogOut)
        btnWithdrawMember = view.findViewById(R.id.btnWithdrawMember)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewPets)

        btnUpdateUser.setOnClickListener {
            replaceFragment(UpdateUserFragment())
        }
        btnAddPet.setOnClickListener {
            replaceFragment(AddPetFragment())
        }
        btnInquiry.setOnClickListener {
            replaceFragment(InquiryFragment())
        }
        btnLogOut.setOnClickListener {
            showLogOutConfirmationDialog()

        }
        btnWithdrawMember.setOnClickListener {
            showWithdrawMemberConfirmationdialog()
        }

        // 목업 데이터 추가
        val petList = listOf(
            Pet("1", R.drawable.testmydog, "코코", R.drawable.clean_dog, "6", R.drawable.male),
            Pet("2", R.drawable.testmycat, "미미", R.drawable.clean_cat, "2", R.drawable.female)
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = PetAdapter(petList) { pet ->
            openNextFragment(pet)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_page, container, false)
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.frame_layout, fragment)
            addToBackStack(null)
        }
    }

    private fun openNextFragment(pet: Pet) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, PetDetailFragment())
            .addToBackStack(null)
            .commit()
    }
    private fun showLogOutConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("로그아웃")
            .setMessage("로그아웃 하시겠습니까?")
            .setPositiveButton("확인") { _, _ ->
                logOutProcess()
            }
            .setNegativeButton("취소", null)
            .show()
    }
    private fun logOutProcess() {
        AlertDialog.Builder(requireContext())
            .setTitle("로그아웃")
            .setMessage("로그아웃이 완료되었습니다.")
            .setPositiveButton("확인") {_, _ ->
                val intent = Intent(requireContext(), StartActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
            .show()
    }
    private fun showWithdrawMemberConfirmationdialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("회원탈퇴")
            .setMessage("회원탈퇴를 하시겠습니까?")
            .setPositiveButton("확인") { _, _ ->
                checkWithdrawMember()
            }
            .setNegativeButton("취소", null)
            .show()
    }
    private fun checkWithdrawMember() {
        AlertDialog.Builder(requireContext())
            .setTitle("회원탈퇴")
            .setMessage("정말 회원탈퇴를 하시겠습니까?")
            .setPositiveButton("확인") { _, _->
                withdrawMemberProcess()
            }
            .setNegativeButton("취소", null)
            .show()
    }
    private fun withdrawMemberProcess() {
        AlertDialog.Builder(requireContext())
            .setTitle("회원탈퇴")
            .setMessage("회원탈퇴가 완료되었습니다.")
            .setPositiveButton("확인") {_, _ ->
                val intent = Intent(requireContext(), StartActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
            .show()
    }
}