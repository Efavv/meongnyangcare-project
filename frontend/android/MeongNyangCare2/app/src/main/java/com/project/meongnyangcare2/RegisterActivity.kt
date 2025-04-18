package com.project.meongnyangcare

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.project.meongnyangcare2.R

class RegisterActivity : AppCompatActivity() {

    private val dummyExistingIds = listOf("user1", "test", "admin") // 목업용 ID

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val editTextUserId = findViewById<EditText>(R.id.editTextRegisterUserId)
        val btnCheckDuplicate = findViewById<Button>(R.id.btnCheckDuplicate)
        val textViewIdCheckResult = findViewById<TextView>(R.id.textViewIdCheckResult)

        val editTextPassword = findViewById<EditText>(R.id.editTextRegisterPassword)
        val editTextPasswordConfirm = findViewById<EditText>(R.id.editTextRegisterPasswordConfirm)
        val textViewPasswordError = findViewById<TextView>(R.id.textViewPasswordError)

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val textViewEmptyFieldsError = findViewById<TextView>(R.id.textViewEmptyFieldsError) // 새로운 에러 메시지 TextView

        // 아이디 중복 확인
        btnCheckDuplicate.setOnClickListener {
            val inputId = editTextUserId.text.toString().trim()

            if (inputId.isEmpty()) {
                showAnimatedText(textViewIdCheckResult, "아이디를 입력하세요.", R.color.red)
            } else if (dummyExistingIds.contains(inputId)) {
                showAnimatedText(textViewIdCheckResult, "이미 존재하는 아이디입니다.", R.color.red)
            } else {
                showAnimatedText(textViewIdCheckResult, "사용 가능한 아이디입니다.", R.color.blue)
            }
        }

        // 회원가입 버튼 클릭
        btnRegister.setOnClickListener {
            val userId = editTextUserId.text.toString().trim()
            val password = editTextPassword.text.toString()
            val passwordConfirm = editTextPasswordConfirm.text.toString()

            // 필수 입력 필드 체크
            if (userId.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
                textViewEmptyFieldsError.text = "모든 항목을 입력해주세요."
                showAnimatedText(textViewEmptyFieldsError, "모든 항목을 입력해주세요.", R.color.red)
                return@setOnClickListener
            } else {
                textViewEmptyFieldsError.text = "" // 메시지 초기화
            }

            // 비밀번호 확인
            if (password != passwordConfirm) {
                showAnimatedText(textViewPasswordError, "비밀번호가 일치하지 않습니다.", R.color.red)
            } else {
                hideTextWithAnimation(textViewPasswordError)

                // 회원가입 성공 후 로그인 화면으로 이동
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("registrationSuccess", true) // 회원가입 성공 여부 전달
                startActivity(intent)
                finish() // 회원가입 화면 종료
            }
        }
    }

    // 에러 메시지 애니메이션으로 보여주기
    private fun showAnimatedText(textView: TextView, message: String, colorResId: Int) {
        textView.text = message
        textView.setTextColor(resources.getColor(colorResId, null))
        if (textView.visibility == View.GONE) {
            textView.alpha = 0f
            textView.visibility = View.VISIBLE
            textView.animate()
                .alpha(1f)
                .setDuration(300)
                .start()
        }
    }

    // 에러 메시지 감추기
    private fun hideTextWithAnimation(textView: TextView) {
        if (textView.visibility == View.VISIBLE) {
            textView.animate()
                .alpha(0f)
                .setDuration(300)
                .withEndAction {
                    textView.visibility = View.GONE
                }
                .start()
        }
    }
}
