package com.charlesmuchene.kotlin.learn.db.converters

import androidx.room.TypeConverter
import com.charlesmuchene.kotlin.learn.models.Currency
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

/**
 * Currency array type converter
 *
 * Could be improved.
 */
class CurrencyArrayConverter {

    private val json = Json(JsonConfiguration.Stable)

    @TypeConverter
    fun fromString(raw: String): Array<Currency> =
        json.parse(Currency.serializer().list, raw).toTypedArray()

    @TypeConverter
    fun toString(items: Array<Currency>): String =
        json.stringify(Currency.serializer().list, items.toList())

}