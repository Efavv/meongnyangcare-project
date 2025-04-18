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

class UpdateNickNameFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.setToolbarTitle("닉네임 변경")

        val btnSaveUser = view.findViewById<Button>(R.id.btnSaveUser)
        val editNickName = view.findViewById<EditText>(R.id.editNickName)
        val errorNickName = view.findViewById<TextView>(R.id.errorNickName)

        btnSaveUser.setOnClickListener {
            val nickName = editNickName.text.toString().trim()

            if(nickName.isEmpty()) {
                errorNickName.visibility = View.VISIBLE
            } else {
                errorNickName.visibility = View.GONE

                AlertDialog.Builder(requireContext())
                    .setTitle("알림")
                    .setMessage("닉네임이 변경되었습니다.")
                    .setPositiveButton("확인") { dialog, _ ->
                        dialog.dismiss()
                        replaceFragment(MyPageFragment())
                    }.show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_nick_name, container, false)
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.frame_layout, fragment)
            addToBackStack(null)
        }
    }
}