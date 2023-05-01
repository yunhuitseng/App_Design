package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Bundle 打包資料  藉由putExtras()
        val bundle = Bundle()
        bundle.putInt("Key1",1)
        bundle.putString("Key2","this is string")


        val intent =Intent(this,MainActivity2::class.java)
        intent.putExtras(bundle)
        startActivityForResult(intent,1)//step1 發送intent

    }

    //使用onActivityResult()方法來，取得返回資料
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //判斷Intent不能為空，檢查資料是否有數值
        data?.extras?.let {

            if (requestCode == 1 && resultCode == Activity.RESULT_OK)
            {
                //show返回資料
                findViewById<TextView>(R.id.textView2).text =
                    "結果1: ${it.getString("Key3")}\n\n" +
                    "結果2: ${it.getString("Key1")}\n\n"


            }
        }
    }
}