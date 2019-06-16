package com.charlesmuchene.kotlin.learn.db.converters

import androidx.room.TypeConverter
import com.charlesmuchene.kotlin.learn.models.Language
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list

/**
 * Language array type converter.
 *
 * Could be improved
 */
class LanguageArrayConverter {

    private val json = Json(JsonConfiguration.Stable)

    @TypeConverter
    fun fromString(raw: String): Array<Language> = json.parse(Language.serializer().list, raw).toTypedArray()

    @TypeConverter
    fun toString(items: Array<Language>): String = json.stringify(Language.serializer().list, items.toList())

}