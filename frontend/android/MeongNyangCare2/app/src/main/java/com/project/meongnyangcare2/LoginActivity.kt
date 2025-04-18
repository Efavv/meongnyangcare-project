package com.project.meongnyangcare

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.project.meongnyangcare2.HomeFragment
import com.project.meongnyangcare2.MainActivity
import com.project.meongnyangcare2.R

class LoginActivity : AppCompatActivity() {

    // 목업 사용자 데이터
    private val mockUserId = "testUser"
    private val mockPassword = "testPass"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextUserId = findViewById<EditText>(R.id.editTextUserId)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val textViewLoginError = findViewById<TextView>(R.id.textViewLoginError)

        val btnRegister = findViewById<Button>(R.id.registerBtn)
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // 로그인 화면에 회원가입 성공 메시지 표시
        val registrationSuccess = intent.getBooleanExtra("registrationSuccess", false)
        if (registrationSuccess) {
            // 회원가입 성공 시 Snackbar 표시
            val rootView = findViewById<View>(android.R.id.content) // 최상위 레이아웃을 rootView로 사용
            Snackbar.make(rootView, "회원가입 성공! 로그인 해주세요.", Snackbar.LENGTH_SHORT)
                .setBackgroundTint(ContextCompat.getColor(this, R.color.snackbar_background)) // 색상 변경 (성공 메시지 색)
                .show()
        }

        // 로그인 버튼 클릭
        btnLogin.setOnClickListener {
            val userId = editTextUserId.text.toString().trim()
            val password = editTextPassword.text.toString()

            // 에러 메시지 초기화
            textViewLoginError.visibility = View.GONE

            // 아이디 및 비밀번호 체크
            if (userId.isEmpty() && password.isEmpty()) {
                showAnimatedText(textViewLoginError, "아이디와 비밀번호를 입력해주세요.", R.color.red)
            } else if (userId.isEmpty()) {
                showAnimatedText(textViewLoginError, "아이디를 입력해주세요.", R.color.red)
            } else if (password.isEmpty()) {
                showAnimatedText(textViewLoginError, "비밀번호를 입력해주세요.", R.color.red)
            } else if (userId != mockUserId || password != mockPassword) {
                showAnimatedText(textViewLoginError, "아이디/비밀번호를 확인해주세요.", R.color.red)
            } else {
                // 로그인 성공 후 MainActivity로 이동
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("loginSuccess", true) // 로그인 성공 여부 전달
                startActivity(intent)
                finish() // 로그인 화면 종료
            }
        }
    }

    // 에러 메시지 애니메이션으로 보여주기
    private fun showAnimatedText(textView: TextView, message: String, colorResId: Int) {
        textView.text = message
        textView.setTextColor(ContextCompat.getColor(this, colorResId)) // 색상 변경
        textView.visibility = View.VISIBLE
        textView.alpha = 0f
        textView.animate()
            .alpha(1f)
            .setDuration(300)
            .start()
    }
}
