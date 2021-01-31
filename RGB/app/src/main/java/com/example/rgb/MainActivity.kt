package com.example.rgb

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var str: String
    lateinit var view : View
    lateinit var editRedText : EditText
    lateinit var editGreenText : EditText
    lateinit var editBlueText : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view = findViewById(R.id.view)
        editRedText = findViewById(R.id.edit_text_red)
        editGreenText = findViewById(R.id.edit_text_green)
        editBlueText = findViewById(R.id.edit_text_blue)
        textChangeListener(editRedText)
        textChangeListener(editGreenText)
        textChangeListener(editBlueText)
    }
    private fun getColor(text: String?):String?{
        if(text=="0" || text=="1" || text=="2" || text=="3" || text=="4" || text=="5" || text=="6" || text=="7" || text=="8" || text=="9") return "0"+text
        else return text
    }
    private fun textChangeListener(editText : EditText){
        editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    if(s.isNotEmpty() && s.toString().toInt() in 0..255) regenerateColor()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
    private fun regenerateColor(){
        view.setBackgroundColor(
                Color.rgb(
                        editRedText.text.toString().toInt(),
                        editGreenText.text.toString().toInt(),
                        editBlueText.text.toString().toInt()
                )
        )
        str = "#"+getColor(Integer.toHexString(editRedText.text.toString().toInt()).toString())+
                getColor(Integer.toHexString(editGreenText.text.toString().toInt()).toString())+
                getColor(Integer.toHexString(editBlueText.text.toString().toInt()).toString())
        textView.text = str
    }
}