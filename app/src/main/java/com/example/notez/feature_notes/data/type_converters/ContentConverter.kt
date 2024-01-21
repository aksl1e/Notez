package com.example.notez.feature_notes.data.type_converters

import androidx.room.TypeConverter
import com.example.notez.feature_notes.domain.model.Content
import com.google.gson.Gson

class ContentConverter {
    @TypeConverter
    fun fromContent(content: Content): String {
        return Gson().toJson(content)
    }

    @TypeConverter
    fun toContent(contentString: String): Content {
        return Gson().fromJson(contentString, Content::class.java)
    }
}