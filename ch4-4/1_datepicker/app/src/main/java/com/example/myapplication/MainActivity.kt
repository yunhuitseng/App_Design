package com.example.myapplication

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button_date = findViewById<Button>(R.id.button)
        var textview_date = findViewById<TextView>(R.id.textView)

        var cal = Calendar.getInstance()//設定日期 get的情況

        //取的設定日期資料
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, month: Int,dayOfMonth: Int) {
                val dateFormat = "yyyy/MM/dd" //設定年月日格式
                val sdf = SimpleDateFormat(dateFormat, Locale.US)//設定時區
                textview_date.text = sdf.format(cal.time)


            }
        }
        //點選按鈕取得 年/月/日
        button_date.setOnClickListener{

            //彈出日期的Dialog，並給定選擇時間
            DatePickerDialog(this@MainActivity,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()

        }

    }
}