package com.example.fristsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //透過findViewById<EditText> 找到activity_main.xml裡面的id名稱，通常接R.id.名稱，取得資料值
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val send = findViewById<Button>(R.id.send)

        //送出指令通常使用setOnClickListener，進行監聽的動作
        send.setOnClickListener {
            val name = nameEditText.text.toString()
            nameTextView.text = name
        }
    }
}