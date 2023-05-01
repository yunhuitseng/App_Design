package com.example.lec0327_3

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button= findViewById<Button>(R.id.button)
        var selectedDateTV = findViewById<TextView>(R.id.textView)

        button.setOnClickListener{

            val cal= Calendar.getInstance()
            var year=cal.get(Calendar.YEAR)
            var month=cal.get(Calendar.MONTH)
            var day=cal.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this,
                { view,year,month,day -> selectedDateTV.text=(year.toString()+"-"+(month+1).toString()+"-"+day.toString())
                },year,month,day)

            datePickerDialog.show()
        }


        //匯入圖片

    }
}