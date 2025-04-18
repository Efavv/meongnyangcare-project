package com.project.meongnyangcare2

import android.os.Bundle
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.project.meongnyangcare2.databinding.ActivityMainBinding
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val BACK_PRESSED_DURATION = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.analysis -> replaceFragment(AnalysisFragment())
                R.id.analysishistory -> replaceFragment(PastDiagnosisFragment())
                R.id.mypage -> replaceFragment(MyPageFragment())
            }
            true
        }

        // 뒤로 가기 이벤트 처리
        val backPressEvent = MutableSharedFlow<Unit>(
            replay = 0,
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )

        onBackPressedDispatcher.addCallback(this) {
            backPressEvent.tryEmit(Unit)
        }

        // 뒤로 가기 두 번 클릭 시 종료
        lifecycleScope.launch {
            backPressEvent
                .scan(listOf(System.currentTimeMillis() - BACK_PRESSED_DURATION)) { acc, _ ->
                    acc.takeLast(1) + System.currentTimeMillis()
                }
                .drop(1)
                .collectLatest {
                    if (it.last() - it.first() < BACK_PRESSED_DURATION) {
                        finishAffinity() // 앱 종료
                    } else {
                        Snackbar.make(binding.root, "뒤로가기를 한 번 더 누르면 종료합니다.", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(getColor(R.color.snackbar_background))
                            .show()
                    }
                }
        }
    }

    fun setToolbarTitle(title: String) {
        findViewById<TextView>(R.id.textViewToolbarTitle).text = title
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}
