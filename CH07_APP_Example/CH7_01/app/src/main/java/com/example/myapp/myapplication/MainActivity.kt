package com.example.myapp.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //使用OkHttp進行調用
        //Get的方式
        val client = OkHttpClient()
        val urlBuilder = "https://purechat.lionfree.net/app/read.php".toHttpUrlOrNull()
            ?.newBuilder()
            ?.addQueryParameter("account", "student_1")
            ?.addQueryParameter("limit", "5")
        val url = urlBuilder?.build().toString()
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
                    println("-----${responseBody}")

                    val jsonObject = JSONObject(responseBody)
                    val dataArray = jsonObject.getJSONArray("data")

                    runOnUiThread {
                        //直接將內容回傳給id名稱為TextView
                        val textView = findViewById<TextView>(R.id.textView2)
                        textView.text = ""
                        for (i in 0 until dataArray.length()) {
                            val chatObject = dataArray.getJSONObject(i)
                            val no = chatObject.getString("no")
                            val timestamp = chatObject.getString("timestamp")
                            val account = chatObject.getString("account")
                            val content = chatObject.getString("content")
                            textView.append("${account} ${timestamp}\n")
                            textView.append("${content}  \n")
                        }
                    }
                } else {
                    println("Request failed")
                    runOnUiThread {
                        //直接將內容回傳給id名稱為TextView
                        findViewById<TextView>(R.id.textView).text = "資料錯誤"
                    }

                }
            }
        })

        //POST data 資料進去
        val send = findViewById<Button>(R.id.button)
        val account = findViewById<TextView>(R.id.account)
        val content = findViewById<TextView>(R.id.content)

        send.setOnClickListener{
            val postAccount= account.text.toString()
            println("postAccount-------${postAccount}")
            val postContent= content.text.toString()
            println("postAccount-------${postContent}")
            //----------------
            val clientPost = OkHttpClient()
            val requestBody = FormBody.Builder()
                .add("account", "${postAccount}")
                .add("content", "${postContent}")
                .build()
            val requestPost = Request.Builder()
                .url("https://purechat.lionfree.net/app/insert.php")
                .post(requestBody)
                .build()

            clientPost.newCall(requestPost).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }
                override fun onResponse(call: Call, response: Response) {
                    val responseBody = response.body?.string()
                    // Handle the response body
                    println("POST-------${responseBody}")
                }
            })
        }
        //----輪巡API----
        val timer = Timer()
        val timerTask = object : TimerTask() {
            override fun run() {
                val client = OkHttpClient()
                val urlBuilder = "https://purechat.lionfree.net/app/read.php?".toHttpUrlOrNull()
                    ?.newBuilder()
                    ?.addQueryParameter("account", "student_1")
                    ?.addQueryParameter("limit", "2")
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
                            println("-----${responseBody}")

                            val jsonObject = JSONObject(responseBody)
                            val dataArray = jsonObject.getJSONArray("data")

                            runOnUiThread {
                                //直接將內容回傳給id名稱為TextView
                                val textView = findViewById<TextView>(R.id.textView2)
                                textView.text = ""
                                for (i in 0 until dataArray.length()) {
                                    val chatObject = dataArray.getJSONObject(i)
                                    val no = chatObject.getString("no")
                                    val timestamp = chatObject.getString("timestamp")
                                    val account = chatObject.getString("account")
                                    val content = chatObject.getString("content")
                                    textView.append("${account} ${timestamp}\n")
                                    textView.append("${content}  \n")
                                }
                            }
                        } else {
                            println("Request failed")
                            runOnUiThread {
                                //直接將內容回傳給id名稱為TextView
                                findViewById<TextView>(R.id.textView).text = "資料錯誤"
                            }

                        }
                    }
                })
            }
        }
        timer.schedule(timerTask, 0, 5000)


    }
}