package com.example.myapp.myapplicationokhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.IOException
import com.google.gson.JsonParser
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TextView1 = findViewById<TextView>(R.id.TextView1).text

        val client = OkHttpClient()
        //[GET]天氣內容URL
        val url = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=CWB-0E04E5F3-BF5F-4269-8390-4D0531793558&locationName=%E5%BD%B0%E5%8C%96%E7%B8%A3&elementName=Wx"
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) = if (response.isSuccessful) {
                val responseBody = response.body?.string()
                println(responseBody)

                val jsonObject = JSONObject(responseBody)

                //取得records的location資料
                val locationsArray = jsonObject.getJSONObject("records").getJSONArray("location")
                //取得locationsArray.0的資料內容
                val ChanghuaObject = locationsArray.getJSONObject(0)
                //取得地區名稱
                val ChanghuaName = ChanghuaObject.getString("locationName")
                println("地區:${ChanghuaName}")
                //取得weatherElement結構
                val weatherElementsArray = ChanghuaObject.getJSONArray("weatherElement")
                val wxObject = weatherElementsArray.getJSONObject(0)
                //取得time結構資料
                val timeArray = wxObject.getJSONArray("time")



                //取得回應回來內容
                runOnUiThread {
                    //直接將內容回傳給id名稱為TextView
                    findViewById<TextView>(R.id.TextView1).text = ChanghuaName.toString()
                    for (i in 0 until timeArray.length()) {
                        val timeObject = timeArray.getJSONObject(i)
                        val startTime = timeObject.getString("startTime")
                        val endTime = timeObject.getString("endTime")
                        val parameterObject = timeObject.getJSONObject("parameter")
                        val parameterName = parameterObject.getString("parameterName")
                        val parameterValue = parameterObject.getString("parameterValue")

                        println("startTime: $startTime, endTime: $endTime, parameterName: $parameterName, parameterValue: $parameterValue")

                        val imageView1 = findViewById<ImageView>(R.id.imageView)
                        val imageView2 = findViewById<ImageView>(R.id.imageView2)
                        val imageView3 = findViewById<ImageView>(R.id.imageView3)

                        if(i==0)
                        {
                            findViewById<TextView>(R.id.TextView2).text = parameterName.toString()
                            if (parameterValue.toInt() == 2) {
                                imageView1.setImageResource(R.drawable.cloudy)
                            }
                            if (parameterValue.toInt() == 3) {
                                imageView1.setImageResource(R.drawable.cloudyday)
                            }
                        }
                        if(i==1)
                        {
                            findViewById<TextView>(R.id.TextView3).text = parameterName.toString()
                            if (parameterValue.toInt() == 2) {
                                imageView2.setImageResource(R.drawable.cloudy)
                            }
                            if (parameterValue.toInt() == 3) {
                                imageView2.setImageResource(R.drawable.cloudyday)
                            }
                        }
                        if(i==2)
                        {
                            findViewById<TextView>(R.id.TextView4).text = parameterName.toString()
                            if (parameterValue.toInt() == 2) {
                                imageView3.setImageResource(R.drawable.cloudy)
                            }
                            if (parameterValue.toInt() == 3) {
                                imageView3.setImageResource(R.drawable.cloudyday)
                            }
                        }
                    }


                }
                } else
                {
                    println("Request failed")
                    runOnUiThread {
                        //直接將內容回傳給id名稱為TextView
                        findViewById<TextView>(R.id.TextView1).text = "資料錯誤"
                    }

            }
        })


    }
}