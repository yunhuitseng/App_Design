package com.example.test0320

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//Toast
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{

            Toast.makeText(this,"Toast提示訊息",Toast.LENGTH_SHORT).show()
        }
//Snackbar
        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener{

            Snackbar.make(it,"Snackbar提示訊息",Snackbar.LENGTH_SHORT)
                .setAction("按鈕"){
                    val intent =Intent(Intent.ACTION_VIEW)
                    intent.data=Uri.parse("https://cse.nsysu.edu.tw/")
                    startActivity(intent)

                }.show()

        }
        //AlertDialog
        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener{

            Toast.makeText(this,"AlertDialog提示訊息",Toast.LENGTH_SHORT).show()
            AlertDialog.Builder(this)
                .setTitle("按鈕AlertDialog")
                .setMessage("AlertDialog內容")
                .setNeutralButton("左邊按鈕"){
                    dialog,which->Toast.makeText(this,"左邊按鈕",Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("中間按鈕"){
                        dialog,which->Toast.makeText(this,"中間按鈕",Toast.LENGTH_SHORT).show()
                }
                .setPositiveButton("右邊按鈕"){
                        dialog,which->Toast.makeText(this,"右邊按鈕",Toast.LENGTH_SHORT).show()
                }.show()
        }
        //AlertDialog2
//        val item= arrayOf("item 1","item 2","item 3","item 4","item 5",)
//        val button4 = findViewById<Button>(R.id.button4)
//        button4.setOnClickListener{
//
//
//            AlertDialog.Builder(this)
//                .setTitle("列表式 AlertDialog")
//                .setMessage("AlertDialog內容")
//                .setNeutralButton("左邊按鈕"){
//                        dialog,which->Toast.makeText(this,"左邊按鈕",Toast.LENGTH_SHORT).show()
//                }
//                .setNegativeButton("中間按鈕"){
//                        dialog,which->Toast.makeText(this,"中間按鈕",Toast.LENGTH_SHORT).show()
//                }
//                .setPositiveButton("右邊按鈕"){
//                        dialog,which->Toast.makeText(this,"右邊按鈕",Toast.LENGTH_SHORT).show()
//                }.show()
//        }

        //ProgressBar2
//        var i=0
//        val button6 = findViewById<Button>(R.id.button6)
//        val progressBar2= findViewById<progressBar>(R.id.progressBar2)


    }
}