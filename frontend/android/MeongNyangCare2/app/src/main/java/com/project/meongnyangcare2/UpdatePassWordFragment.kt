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


class UpdatePassWordFragment : Fragment() {


    private lateinit var editTextCurrentPassword: EditText
    private lateinit var etUpdatePassword: EditText
    private lateinit var etUpdatePasswordConfirm: EditText
    private lateinit var tvPWCheckResult: TextView
    private lateinit var textViewPasswordError: TextView
    private lateinit var btnCheckDuplicate: Button
    private lateinit var btnSaveUser: Button

    // 예제: 실제 구현에서는 현재 로그인된 사용자의 비밀번호를 가져와야 함
    private val correctPassword = "1234" // 이 값은 서버에서 가져와야 함

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.setToolbarTitle("비밀번호 변경")

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_pass_word, container, false)

        editTextCurrentPassword = view.findViewById(R.id.editTextRegisterUserId)
        etUpdatePassword = view.findViewById(R.id.etUpdatePassword)
        etUpdatePasswordConfirm = view.findViewById(R.id.etUpdatePasswordConfirm)
        tvPWCheckResult = view.findViewById(R.id.tvPWCheckResult)
        textViewPasswordError = view.findViewById(R.id.textViewPasswordError)
        btnCheckDuplicate = view.findViewById(R.id.btnCheckDuplicate)
        btnSaveUser = view.findViewById(R.id.btnSaveUser)

        // 비밀번호 확인 버튼 클릭 이벤트
        btnCheckDuplicate.setOnClickListener {
            val enteredPassword = editTextCurrentPassword.text.toString()

            if (enteredPassword == correctPassword) {
                tvPWCheckResult.visibility = View.GONE
                etUpdatePassword.isEnabled = true
                etUpdatePasswordConfirm.isEnabled = true
            } else {
                tvPWCheckResult.text = "현재 비밀번호가 올바르지 않습니다."
                tvPWCheckResult.visibility = View.VISIBLE
                etUpdatePassword.isEnabled = false
                etUpdatePasswordConfirm.isEnabled = false
            }
        }

        // 비밀번호 변경 버튼 클릭 이벤트
        btnSaveUser.setOnClickListener {
            val newPassword = etUpdatePassword.text.toString()
            val confirmPassword = etUpdatePasswordConfirm.text.toString()

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                textViewPasswordError.text = "새 비밀번호를 입력해주세요."
                textViewPasswordError.visibility = View.VISIBLE
                return@setOnClickListener
            }

            if (newPassword != confirmPassword) {
                textViewPasswordError.text = "비밀번호가 일치하지 않습니다."
                textViewPasswordError.visibility = View.VISIBLE
            } else {
                textViewPasswordError.visibility = View.GONE
                changePassword()
            }
        }

        return view
    }

    private fun changePassword() {
        AlertDialog.Builder(requireContext())
            .setTitle("비밀번호 변경")
            .setMessage("비밀번호가 성공적으로 변경되었습니다.")
            .setPositiveButton("확인") { _, _ ->
                replaceFragment(UpdateUserFragment())
            }
            .show()
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.frame_layout, fragment)
            addToBackStack(null)
        }
    }
}