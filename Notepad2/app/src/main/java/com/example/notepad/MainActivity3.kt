package com.example.notepad

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.activity_main3.save

class MainActivity3 : AppCompatActivity() {
    private var str: String? = null
    private var color: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        Color.RGBToHSV(Repository.red!!,Repository.green!!,Repository.blue!!, Repository.hsv)
        edit_hue.setText(Repository.hsv.get(0).toString())
        edit_saturation.setText(Repository.hsv.get(1).toString())
        edit_value.setText(Repository.hsv.get(2).toString())
        rgb.setOnClickListener{
            fillHsv()
            goToMainActivity2()
            Repository.bool = 1
        }
        save.setOnClickListener{
            fillHsv()
            str = "HSV: ( "+(Math.round(Repository.hsv.get(0) * 10.000) / 10.000)+" , "+(Math.round(Repository.hsv.get(1) * 10.000) / 10.000)+" , "+(Math.round(Repository.hsv.get(2) * 10.000) / 10.000)+" )"
            color = extract(Color.HSVToColor(Repository.hsv)).toString()
            Repository.color?.add(color!!)
            Repository.content?.add(str!!)
            saveIt()
            Repository.arrayList?.add(Items(color,str))
            goToMainActivity()
        }
    }
    private fun saveIt() {
        val sharedPreferences = this.getSharedPreferences("SharedIt", Context.MODE_PRIVATE)
        sharedPreferences?.let { sp ->
            val editor = sp.edit()
            editor.putStringSet("colorList",Repository.color.toSet())
            editor.putStringSet("contentList",Repository.content.toSet())
            editor.apply()
        }
    }
    private fun fillHsv(){
        Repository.hsv[0] = edit_hue.text.toString().toFloat()
        Repository.hsv[1] = edit_saturation.text.toString().toFloat()
        Repository.hsv[2] = edit_value.text.toString().toFloat()
    }
    private fun getColorCode(text: String?): String?{
        if(text=="0" || text=="1" || text=="2" || text=="3" || text=="4" || text=="5" || text=="6" || text=="7" || text=="8" || text=="9") return "0"+text
        else return text
    }
    private fun extract(color: Int?) : String? {
       val str = "#"+getColorCode(Integer.toHexString(Color.red(color!!)).toString())+getColorCode(Integer.toHexString(Color.green(color!!)).toString())+
                getColorCode(Integer.toHexString(Color.blue(color!!)).toString())
        return str
    }
    private fun goToMainActivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    private fun goToMainActivity2(){
        val intent = Intent(this,MainActivity2::class.java)
        startActivity(intent)
    }
}