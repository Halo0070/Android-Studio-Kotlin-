package com.example.tiltsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSensorChanged(event: SensorEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // 센서 등록
    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,        // registerListener() 메서드로 사용할 센서를 등록, this를 지정하여 액티비티에서 센서값을 받도록 함.
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),  // getDefaultSensor() 메서드로 사용할 센서 종류를 지정함. Sensor 클래스에 상수로 정의된 가속도(TYPE_ACCELEROMETER)를 지정함.
            SensorManager.SENSOR_DELAY_NORMAL)      // 세 번째 인자는 센서값을 얼마나 자주 받을지를 지정함. SensorManager 클래스에 정의된 상수 중 하나를 선택함.
    }

    // 센서 해제
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}
