package com.example.notepad

interface Repository {
    companion object{
        var bool: Int? = -1
        var red: Int? = 0
        var green: Int? = 0
        var blue: Int? = 0
        var color = mutableListOf<String>()
        var content = mutableListOf<String>()
        var arrayList = ArrayList<Items>()
        var hsv = FloatArray(3)
    }
}