package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultButton.setOnClickListener{
            // 결과 버튼이 클릭되면 할 일을 작성하는 부분
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }
}
