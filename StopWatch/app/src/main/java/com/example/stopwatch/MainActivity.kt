package com.example.stopwatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var time = 0    // 시간을 계산할 변수를 0으로 초기화
    private var isRunning = false
    private var timerTask: Timer? = null    // timerTask 변수를 null을 허용하는 Timer 타입으로 선언
    private var lap = 1     // 변수 lap을 1로 초기화하여 선언

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
        fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)   // 타이머 시작과 반대로 FAB를 클릭하면 이미지를 시작 이미지로 교체
        timerTask?.cancel()     // 실행 중인 타이머가 있다면 타이머를 취소
    }

    //타이머 시작 구현
    private fun  start() {
        fab.setImageResource(R.drawable.ic_pause_black_24dp)    // 타이머를 시작하는 FAB를 누르면 FAB의 이미지를 일시정지 이미지로 변경

        timerTask = timer(period = 10) {    // Timer 객체를 변수에 저장
            time++                          // 계산한 초와 밀리초를 각각의 텍스트 뷰에 설정
            val sec = time / 100
            val milli = time % 100
            runOnUiThread{                  // runOnUiThread로 감싸서 UI 조작이 가능하게 함.
                secTextView.text = "$sec"
                milliTextView.text = "$milli"
            }
        }
    }

    //랩 타임 기록 메서드
    private fun recordLapTime() {
        val lapTime = this.time         // 현재 시간을 지역 변수에 저장
        val textView = TextView(this)   // 동적으로 TextView를 생성
        textView.text = "$lap LAB : ${lapTime / 100}.${lapTime % 100}"  // '1 LAB : 5.35'와 같은 형태가 되도록 시간을 계산하여 문자열로 설정

        // 맨 위에 랩타임 추가      // 맨 위에 추가하고 lap 변수는 다음을 위해 1만큼 증가(private var lap = 1)
        lapLayout.addView(textView, 0)
        lap++
    }
}
