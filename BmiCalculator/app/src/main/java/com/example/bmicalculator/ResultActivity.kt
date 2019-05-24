package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("weight", weightEditText.text.toString())
        intent.putExtra("height", heightEditText.text.toString())
        startActivity(intent)
    }
}
