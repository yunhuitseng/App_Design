package com.example.lec4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //判斷Intent 不為空，檢查是否有帶入資料
        intent?.extras?.let {
            val value = it.getInt("Key")
            val textView2 = findViewById<TextView>(R.id.textView)
            textView2.text = value.toString()
        }
    }
}