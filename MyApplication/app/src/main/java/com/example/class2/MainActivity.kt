                                            package com.example.class2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent()
        intent.setClass(this,MainActivity2::class.java)
        startActivity(intent)

        //val button = findViewById<Button>(R.id.button)
        //button.setOn

    }
}