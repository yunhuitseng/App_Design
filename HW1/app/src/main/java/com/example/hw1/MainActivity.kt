package com.example.hw1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text1 =findViewById<EditText>(R.id.editTextTextPersonName)
        val text2 =findViewById<EditText>(R.id.editTextTextPersonName2)
        val textview4 = findViewById<TextView>(R.id.textView4)
        //判斷intent是否為空
        intent?.extras?.let {
            //接收變數
            val k1 = it.getString("Key1").toString()
            val k2 = it.getString("Key2").toString()
            val value3 = it.getString("Key3").toString()
            val value4 = it.getString("key4").toString()
            //show變數
            text1.setText(k1)
            text2.setText(k2)
            textview4.setText( value3 +"\n\n"+ value4)

        }

        //按送出傳遞名字、性別資訊
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{


            var t1=text1.text.toString()
            var t2=text2.text.toString()
            //Bundle傳送多個數值
            var bundle = Bundle()
            bundle.putString("Key1", t1)
            bundle.putString("Key2",t2)
            //intent 切換 activity
            val intent = Intent(this,MainActivity2::class.java)
            //藉由putExtras()傳送打包好的bundle資料
            intent.putExtras(bundle)
            startActivityForResult(intent,1)
        }



    }


}