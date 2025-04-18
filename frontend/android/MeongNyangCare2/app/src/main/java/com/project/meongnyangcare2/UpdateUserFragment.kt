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
import android.widget.Toast
import androidx.fragment.app.commit


class UpdateUserFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.setToolbarTitle("회원정보 수정")

        val btnUpdateNickName = view.findViewById<Button>(R.id.btnUpdateNickName)
        val btnUpdatePassword = view.findViewById<Button>(R.id.btnUpdatePassWord)

        btnUpdateNickName.setOnClickListener {
            replaceFragment(UpdateNickNameFragment())
        }
        btnUpdatePassword.setOnClickListener {
            replaceFragment(UpdatePassWordFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_user, container, false)
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.frame_layout, fragment)
            addToBackStack(null)
        }
    }
}