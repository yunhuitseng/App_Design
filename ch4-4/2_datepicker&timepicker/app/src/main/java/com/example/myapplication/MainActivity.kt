package com.example.myapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button = findViewById<Button>(R.id.button)
        var selectedDateTV = findViewById<TextView>(R.id.textView)

        button.setOnClickListener {
            val cal = Calendar.getInstance()//設定日期 get的情況
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(this,
                { view, year, month, day ->
                    selectedDateTV.text =(year.toString()+ "-" + (month + 1).toString() + "-" + day.toString())
                    //月份+1主要是 Java月份從0開始，所以需要補1，才會是一般了解月份
                },year,month,day)
            datePickerDialog.show()
        }

        var button2 = findViewById<Button>(R.id.button2)
        var selectedTimeTV = findViewById<TextView>(R.id.textView2)
        button2.setOnClickListener {
            val cal = Calendar.getInstance()
            val hour = cal.get(Calendar.HOUR_OF_DAY)
            val minute = cal.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(
                this,
                { view, hourOfDay, minute ->selectedTimeTV.setText("$hourOfDay:$minute")
                },hour,minute,
                //true
                false
            )
            timePickerDialog.show()
        }
    }

}