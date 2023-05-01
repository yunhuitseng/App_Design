package com.example.myapp.vegetable

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.database.sqlite.SQLiteDatabase
import android.widget.*

class MainActivity : AppCompatActivity() {

    private var items: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var dbrw: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //取得資料庫實體
        dbrw = MyDBHelper(this).writableDatabase
        //宣告 Adapter 並連結 ListView
        adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, items)
        findViewById<ListView>(R.id.listView).adapter = adapter
        //設定監聽器
        setListener()

        //新增
        //方法1: 建立ContentValues物件，用於存放要新增的資料
        val cv = ContentValues()
        cv.put("vegetable","A菜")
        cv.put("price",35).toString()
        dbrw.insert("myTable",null,cv)

        //方法2: SQL語法
        dbrw.execSQL("INSERT INTO myTable(vegetable, price) VALUES(?,?)",
            arrayOf("地瓜葉", 35))

        //修改
        //方法1: 建立ContentValues物件，用於存放要修改的資料
        cv.put("price",40).toString()
        dbrw.update("myTable",cv,"vegetable='A菜'",null)

        //方法2: SQL語法
        dbrw.execSQL("UPDATE myTable SET price = 40 WHERE vegetable LIKE 'A菜'")

        //刪除
        //方法1: 使用delete()刪除資料
        dbrw.delete("myTable","vegetable='A菜'",null)

        //方法2: SQL語法
        dbrw.execSQL("DELETE FROM myTable WHERE vegetable LIKE 'A菜'")

        //查詢
        val c = dbrw.rawQuery("SELECT * FROM myTable WHERE vegetable LIKE '地瓜葉'",null)

    }
    override fun onDestroy() {
        dbrw.close() //關閉資料庫
        super.onDestroy()
    }
    //設定監聽器
    private fun setListener() {
        val vt_name = findViewById<EditText>(R.id.vt_name)
        val vt_price = findViewById<EditText>(R.id.vt_price)
        findViewById<Button>(R.id.btn_insert).setOnClickListener {
            //判斷是否有填入蔬菜或價格
            if (vt_name.length() < 1 || vt_price.length() < 1)
                showToast("欄位請勿留空")
            else
                try {
                    //新增一筆蔬菜紀錄於 myTable 資料表
                    dbrw.execSQL(
                        "INSERT INTO myTable(vegetable, price) VALUES(?,?)",
                        arrayOf(vt_name.text.toString(),
                            vt_price.text.toString())
                    )
                    showToast("新增:${vt_name.text},價格:${vt_price.text}")
                    cleanEditText()
                } catch (e: Exception) {
                    showToast("新增失敗:$e")
                }
        }
        findViewById<Button>(R.id.btn_update).setOnClickListener {
            //判斷是否有填入蔬菜或價格
            if (vt_name.length() < 1 || vt_price.length() < 1)
                showToast("欄位請勿留空")
            else
                try {
                    //尋找相同蔬菜的紀錄並更新 price 欄位的值
                    dbrw.execSQL("UPDATE myTable SET price = ${vt_price.text} WHERE vegetable LIKE '${vt_name.text}'")
                    showToast("更新:${vt_name.text},價格:${vt_price.text}")
                    cleanEditText()
                } catch (e: Exception) {
                    showToast("更新失敗:$e")
                }
        }
        findViewById<Button>(R.id.btn_delete).setOnClickListener {
            //判斷是否有填入書名
            if (vt_name.length() < 1)
                showToast("蔬菜請勿留空")
            else
                try {
                    //從 myTable 資料表刪除相同蔬菜的紀錄
                    dbrw.execSQL("DELETE FROM myTable WHERE vegetable LIKE '${vt_name.text}'")
                    showToast("刪除:${vt_name.text}")
                    cleanEditText()
                } catch (e: Exception) {
                    showToast("刪除失敗:$e")
                }
        }
        findViewById<Button>(R.id.btn_query).setOnClickListener {
            //若無輸入蔬菜則 SQL 語法為查詢全部書籍，反之查詢該書名資料
            val queryString = if (vt_name.length() < 1)
                "SELECT * FROM myTable"
            else
                "SELECT * FROM myTable WHERE vegetable LIKE '${vt_name.text}'"
            val c = dbrw.rawQuery(queryString, null)
            c.moveToFirst() //從第一筆開始輸出
            items.clear() //清空舊資料
            showToast("共有${c.count}筆資料")
            for (i in 0 until c.count) {
                //加入新資料
                items.add("蔬菜:${c.getString(0)}\t\t\t\t 價格:${c.getInt(1)}")
                c.moveToNext() //移動到下一筆
            }
            adapter.notifyDataSetChanged() //更新列表資料
            c.close() //關閉 Cursor
        }
    }
    //建立 showToast 方法顯示 Toast 訊息
    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()
    //清空輸入的蔬菜與價格
    private fun cleanEditText() {
        findViewById<EditText>(R.id.vt_name).setText("")
        findViewById<EditText>(R.id.vt_price).setText("")
    }


}