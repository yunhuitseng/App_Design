package com.example.myapp.myapplicationokhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val client = OkHttpClient()
        //[GET]地震內容URL
        val url = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/E-A0015-001?Authorization=CWB-0E04E5F3-BF5F-4269-8390-4D0531793558"
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
                    //地震號碼----------------------------------------------
                    val earthquakeNo = jsonObject
                        .getAsJsonObject("records")
                        .getAsJsonArray("Earthquake")[0]
                        .asJsonObject
                        .get("EarthquakeNo")
                        .asInt
                    println("號碼---------${earthquakeNo}")
                    //地震顏色等級------------------------------------------
                    val ReportColor = jsonObject
                        .getAsJsonObject("records")
                        .getAsJsonArray("Earthquake")[0]
                        .asJsonObject
                        .get("ReportColor")
                        .asString
                    println("地震顏色等級---------${ReportColor}")
                    //地震發生位置------------------------------------------
                    val ReportContent = jsonObject
                        .getAsJsonObject("records")
                        .getAsJsonArray("Earthquake")[0]
                        .asJsonObject
                        .get("ReportContent")
                        .asString
                    println("地震發生位置---------${ReportContent}")
                    //地震圖片------------------------------------------
                    val ReportImageURI = jsonObject
                        .getAsJsonObject("records")
                        .getAsJsonArray("Earthquake")[0]
                        .asJsonObject
                        .get("ReportImageURI")
                        .asString
                    println("ReportImageURI---------${ReportImageURI}")

                    val imageView = findViewById<ImageView>(R.id.imageView)

                    //取得回應回來內容
                    runOnUiThread {
                        //直接將內容回傳給id名稱為TextView
                        findViewById<TextView>(R.id.TextView1).text = earthquakeNo.toString()
                        findViewById<TextView>(R.id.TextView2).text = ReportColor.toString()
                        findViewById<TextView>(R.id.TextView3).text = ReportContent.toString()
                        Picasso.get().load("${ReportImageURI}").into(imageView)

                    }
                } else {
                    println("Request failed")
                    runOnUiThread {
                        //直接將內容回傳給id名稱為TextView
                        findViewById<TextView>(R.id.TextView1).text = "資料錯誤"
                    }

                }
            }
        })
    }
}