package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast

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

        val gridView = findViewById<GridView>(R.id.gridView)
        //建立一個ArrayList用來存放文字跟圖片
        val grid= ArrayList<HashMap<String, Any>>()
        //建立一個for迴圈  數量從travelNames.indice取得
        for (i in travelNames.indices) {
            println("travelNames.indices資訊是:${travelNames.indices}")
            val map = HashMap<String, Any>()

            // map內容會從相關array取得並放到list裡面
            map["travelName"] = travelNames[i]
            map["travelImage"] = travelImageIds[i]
            //將HashMap的內容加入到grid裡面
            grid.add(map)

        }
        val fromData=arrayOf("travelName","travelImage")
        val toData= intArrayOf(R.id.textView,R.id.imageView)
        //Adapter用來顯示這頁面的grid，但內容是從grid_row_items產生得
        val simpleAdapter= SimpleAdapter(this,grid,R.layout.grid_row_items,fromData,toData)

        gridView.adapter = simpleAdapter
        gridView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this,"點選的景點 ${travelNames[i]}",Toast.LENGTH_SHORT).show()
        }

    }
}