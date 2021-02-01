package com.example.notepad

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    private var str: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        addText()
        if(Repository.bool==1){
            extract(Color.HSVToColor(Repository.hsv))
        }
        hsv.setOnClickListener{
            addText()
            goToMainActivity3()
        }
        save.setOnClickListener{
            addText()
            str = "RGB: ("+Repository.red+","+Repository.green+","+Repository.blue+")"
            Repository.color?.add(color.text.toString())
            Repository.content?.add(str!!)
            saveIt()
            Repository.arrayList?.add(Items(color.text.toString(),str))
            goToMainActivity()
        }
        listenerEvent(seek_bar_red,edit_red)
        listenerEvent(seek_bar_green,edit_green)
        listenerEvent(seek_bar_blue,edit_blue)
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
    private fun getColorCode(text: String?): String?{
        if(text=="0" || text=="1" || text=="2" || text=="3" || text=="4" || text=="5" || text=="6" || text=="7" || text=="8" || text=="9") return "0"+text
        else return text
    }
    private fun listenerEvent(seekbar: SeekBar, editText: TextView){
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                editText.text = seekbar.progress.toString()
                addText()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?){}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
    private fun setViewColor(){
        view.setBackgroundColor(
                Color.rgb(
                        edit_red.text.toString().toInt(),
                        edit_green.text.toString().toInt(),
                        edit_blue.text.toString().toInt()
                )
        )
        color.text = "#"+getColorCode(Integer.toHexString(seek_bar_red.progress.toString().toInt()).toString())+
                getColorCode(Integer.toHexString(seek_bar_green.progress.toString().toInt()).toString())+
                getColorCode(Integer.toHexString(seek_bar_blue.progress.toString().toInt()).toString())
    }
    private fun addText(){
        Repository.red = edit_red.text.toString().toInt()
        Repository.green = edit_green.text.toString().toInt()
        Repository.blue = edit_blue.text.toString().toInt()
        setViewColor()
    }
    private fun extract(color: Int?){
        edit_red.text = Color.red(color!!).toString()
        edit_green.text = Color.green(color!!).toString()
        edit_blue.text = Color.blue(color!!).toString()
        seek_bar_red.setProgress(edit_red.text.toString().toInt())
        seek_bar_green.setProgress(edit_green.text.toString().toInt())
        seek_bar_blue.setProgress(edit_blue.text.toString().toInt())
        addText()
    }
    private fun goToMainActivity3(){
        val intent = Intent(this, MainActivity3::class.java)
        startActivity(intent)
    }
    private fun goToMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}