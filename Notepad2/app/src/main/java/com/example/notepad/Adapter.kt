package com.example.notepad

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class Adapter(var context: Context, var list: ArrayList<Items>) : BaseAdapter() {
    private lateinit var color: TextView
    private lateinit var content: TextView
    private lateinit var view1: View
    override fun getCount(): Int {
        return list.size
    }
    override fun getItem(position: Int): Any {
        return list.get(position)
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = View.inflate(context,R.layout.layout,null)
        view1 = view.findViewById(R.id.view1)
        color = view.findViewById(R.id.color)
        content = view.findViewById(R.id.content)
        var items: Items = list.get(position!!)
        color.text = items.color
        content.text = items.content
        view1.setBackgroundColor(Color.parseColor(items.color))
        return view!!
    }
}