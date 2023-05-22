package com.example.myapp.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException
import com.squareup.picasso.Picasso
import java.util.*

private val image=arrayOf(R.id.imageView,R.id.imageView1,R.id.imageView)

private val text=arrayOf(R.id.textView,R.id.textView1,R.id.textView1)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //使用OkHttp進行Get屬於帳號student_1的照片，限制為2張(之後會改成全部都顯示)
        val client = OkHttpClient()

        val urlBuilder = "http://10.0.2.2/app/read.php".toHttpUrlOrNull()
            ?.newBuilder()
            ?.addQueryParameter("account", "student_1")
            ?.addQueryParameter("limit", "2")
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

                    val len = dataArray.length() //查詢結果的資料筆數
                    val imgarr = arrayOfNulls<String>(len) //宣告array存圖片位址
                    val textarr = arrayOfNulls<String>(len) //宣告array存圖片title

                    //一筆一筆讀出get到的內容並顯示在ImageView TextView
                    runOnUiThread {
                        for (i in 0 until dataArray.length()) {

                            val chatObject = dataArray.getJSONObject(i)
                            //取得所有資料
                            val imgtitle = chatObject.getString("imgtitle")
                            val upload_date = chatObject.getString("upload_date")
                            val account = chatObject.getString("account")
                            val content = chatObject.getString("content")
                            //圖片在本機上的路徑
                            imgarr[i] = "http://10.0.2.2/app/images/${imgtitle}"
                            textarr[i] = "${content}"
                            println(textarr[i])
                            val imageV = findViewById<ImageView>(image[i])
                            val textV = findViewById<TextView>(text[i])
                            //利用Picasso將圖片網址顯示在ImageView上
                            Picasso.get().load(imgarr[i]).into(imageV)
                            textV.text = textarr[i]
                        }
                    }
                } else {
                    println("Request failed")
                }
            }
        })
    }
}

