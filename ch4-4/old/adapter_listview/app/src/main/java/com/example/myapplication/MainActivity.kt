package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {

    //建立Array
    private val travelNames=arrayOf("kyoto","mount_fuji","okinawa","sensoji_temple","tokyo_disney","tokyo_tower","itsukushima")
    //建立一個旅遊相片id
    private val travelImageIds=arrayOf(R.drawable.kyoto,
        R.drawable.mount_fuji,
        R.drawable.okinawa,
        R.drawable.sensoji_temple,
        R.drawable.tokyo_disney,
        R.drawable.tokyo_tower,
        R.drawable.itsukushima,
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)
        //建立一個ArrayList用來存放文字跟圖片
        val list = ArrayList<HashMap<String, Any>>()
        //建立一個for迴圈  數量從travelNames.indice取得
        for (i in travelNames.indices) {
            val map = HashMap<String, Any>()

            // map內容會從相關array取得並放到list裡面
            map["travelName"] = travelNames[i]
            map["travelImage"] = travelImageIds[i]

            //將HashMap的內容加入到list裡面
            list.add(map)

        }
        val fromData=arrayOf("travelName","travelImage")
        val toDate= intArrayOf(R.id.textView,R.id.imageView)
        //Adapter用來顯示這頁面的list，但內容是從list_row_items產生得
        val simpleAdapter= SimpleAdapter(this,list,R.layout.list_row_items,fromData,toDate)

        listView.adapter = simpleAdapter

    }
}