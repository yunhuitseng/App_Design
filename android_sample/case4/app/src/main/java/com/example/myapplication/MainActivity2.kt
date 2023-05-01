package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        intent?.extras?.let {

            val value1 = it.getInt("Key1")
            val textView1 =findViewById<TextView>(R.id.textView4)
            textView1.text= value1.toString()
            val value2 = it.getString("Key2")


            val bundle = Bundle()
            bundle.putString("Key3","value")
            bundle.putString("Key1","111")
            val intent = Intent().putExtras(bundle)
            setResult(Activity.RESULT_OK, intent.putExtras(bundle))
            finish()//結束自己週期
        }
    }




}