package com.example.hw1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //傳出去第一個頁面


        //打包資料
        val Text3 =findViewById<EditText>(R.id.editTextTextPersonName3)
        val Text4 =findViewById<EditText>(R.id.editTextTextPersonName4)


            //按下才發送到第一頁
            val button = findViewById<Button>(R.id.button2)
            button.setOnClickListener{
                var bundle = Bundle()

                intent?.extras?.let {
                    //接收數值
                    val value1 = it.getString("Key1")
                    val value2 = it.getString("Key2")
                    bundle.putString("Key1", value1)
                    bundle.putString("Key2", value2)
                }



                bundle.putString("Key3", Text3.text.toString())
                bundle.putString("key4", Text4.text.toString())

                val intent = Intent(this,MainActivity::class.java)
                intent.putExtras(bundle)
                startActivityForResult(intent,1)
            }



    }
}