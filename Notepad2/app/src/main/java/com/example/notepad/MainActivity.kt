package com.example.notepad

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: Adapter
    private lateinit var arrayList: ArrayList<Items>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(Repository.bool == -1) loadData()
        listView = findViewById(R.id.list)
        rgb.setOnClickListener{
            goToMainActivity2()
        }
        hsv.setOnClickListener{
            goToMainActivity3()
        }
        adapter = Adapter(this,Repository.arrayList!!)
        listView?.adapter = adapter
    }
    private fun loadData() {
        val sharedPreferences = this.getSharedPreferences("SharedIt",Context.MODE_PRIVATE)
        Repository.color = sharedPreferences?.getStringSet("colorList",null)?.toMutableList()?: mutableListOf()
        Repository.content = sharedPreferences?.getStringSet("contentList",null)?.toMutableList()?: mutableListOf()
        for(index in Repository.color.indices) Repository.arrayList?.add(Items(Repository.color.get(index),Repository.content.get(index)))
        Repository.bool = 0
    }
    private fun goToMainActivity2() {
        val intent = Intent(this,MainActivity2::class.java)
        startActivity(intent)
    }
    private fun goToMainActivity3() {
        val intent = Intent(this,MainActivity3::class.java)
        startActivity(intent)
    }
}