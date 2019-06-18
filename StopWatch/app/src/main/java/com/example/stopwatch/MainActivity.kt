package com.example.stopwatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var time = 0    // 시간을 계산할 변수를 0으로 초기화
    private var isRunning = false
    private var timerTask: Timer? = null    // timerTask 변수를 null을 허용하는 Timer 타입으로 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener{
            isRunning = !isRunning  // FAB이 클릭되면 타이머가 동작 중인지를 저장하는 isRunning 변수의 값을 반전시킴

            if(isRunning) {     // 타이머를 시작 또는 일시정지 시킴.
                start()
            } else {
                pause()
            }
        }
    }

    //타이머 일시정지 구현
    private fun pause() {
        fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        timerTask?.cancel()
    }
}
