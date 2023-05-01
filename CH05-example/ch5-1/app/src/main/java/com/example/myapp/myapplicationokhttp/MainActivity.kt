package com.example.myapp.myapplicationokhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val client = OkHttpClient()
        //[GET]地震內容URL
        val url = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/E-A0015-001?Authorization=CWB-5CEE4F31-A47D-466F-A203-2AF3E8113E57"
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    println(responseBody)

                    val gson = Gson()
                    val jsonObject = gson.fromJson(responseBody, JsonObject::class.java)
                    val earthquakeNo = jsonObject
                        .getAsJsonObject("records")
                        .getAsJsonArray("Earthquake")[0]
                        .asJsonObject
                        .get("EarthquakeNo")
                        .asInt
                    println("號碼---------${earthquakeNo}")
                    //取得回應回來內容
                    runOnUiThread {
                        //直接將內容回傳給id名稱為TextView
                        findViewById<TextView>(R.id.TextView).text = responseBody
                    }
                } else {
                    println("Request failed")
                    runOnUiThread {
                        //直接將內容回傳給id名稱為TextView
                        findViewById<TextView>(R.id.TextView).text = "資料錯誤"
                    }

                }
            }
        })
    }
}