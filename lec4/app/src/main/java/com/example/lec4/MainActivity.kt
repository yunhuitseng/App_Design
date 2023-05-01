package com.example.lec4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //切換
//        val intent = Intent()
//        intent.setClass(this, MainActivity2::class.java)
//        startActivity(intent)
        //button 切換
//        val button = findViewById<Button>(R.id.button)
//        button.setOnClickListener {
//
//            startActivity(intent.setClass(this,MainActivity2::class.java))
//        }
//        //傳直
//        val button = findViewById<Button>(R.id.button)
//        button.setOnClickListener {
//            //startActivity(Intent(this,MainActivity2::class.java))
//            val intent = Intent(this, MainActivity2::class.java)
//            intent.putExtra("Key", 123)
//            startActivity(intent)
//        }


    }
}