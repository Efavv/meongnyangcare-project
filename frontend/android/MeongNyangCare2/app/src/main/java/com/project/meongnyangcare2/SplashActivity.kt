package com.project.meongnyangcare

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.project.meongnyangcare2.MainActivity
import com.project.meongnyangcare2.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val appNameTextView: TextView = findViewById(R.id.name)
        val subNameTextView: TextView = findViewById(R.id.subName)

        // Bold 폰트 적용은 xml에서 설정했으므로 코드로는 별도 처리 불필요

        // 3초 후 메인화면으로 이동 (예시 MainActivity)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
