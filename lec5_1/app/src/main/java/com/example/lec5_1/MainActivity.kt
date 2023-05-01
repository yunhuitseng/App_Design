package com.example.lec5_1

import android.os.Bundle
import android.telecom.Call
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import javax.security.auth.callback.Callback


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient()
        //[GET]天氣內容URL
        val url ="https://opendata.cwb.gov.tw/api/v1/rest/datastore/E-A0015-001?Authorization=CWB-5CEE4F31-A47D-466F-A203-2AF3E8113E57&limit=10&format=JSON&locationName=%E5%BD%B0%E5%8C%96%E7%B8%A3"

        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {

            fun onFailure(call: Call, e: IOException) {
                e.printstackTrace()
            }

            fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {

                    val responseBody = response.body?.string()
                    println(responseBody)
                    runOnUiThread{
                        findViewById<TextView>(R.id.TextView).text= responseBody
                    }
                }
                else {
                    println("Request failed")
                    runOnUiThread{
                        findViewById<TextView>(R.id.TextView).text= "資料錯誤"
                    }
                }

            }
        })




    }

}