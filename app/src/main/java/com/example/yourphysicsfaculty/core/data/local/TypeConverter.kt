package com.example.yourphysicsfaculty.core.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson


/**
 *  Converts String to List of strings
 */
class Converters {

    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}