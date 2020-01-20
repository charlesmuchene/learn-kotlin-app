package com.charlesmuchene.kotlin.learn.db.converters

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list
import kotlinx.serialization.serializer

/**
 *
 * String array converter
 *
 * TODO: This class is hacky!! Please fix it.
 *
 */
class StringArrayConverter {

    private val json = Json(JsonConfiguration.Stable)

    @TypeConverter
    fun fromString(raw: String): Array<String> = json.parse(String.serializer().list, raw).toTypedArray()

    @TypeConverter
    fun toString(items: Array<String>): String = json.stringify(String.serializer().list, items.toList())

}