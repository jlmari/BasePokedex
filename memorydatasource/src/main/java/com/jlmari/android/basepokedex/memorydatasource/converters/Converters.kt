package com.jlmari.android.basepokedex.memorydatasource.converters

import androidx.room.TypeConverter

/**
 * Utility class used to convert data that SQL don't know how to serialize.
 */
class Converters {
    @TypeConverter
    fun fromList(from: List<String>): String = from.joinToString(separator = ",")

    @TypeConverter
    fun toList(from: String): List<String> = from.split(",")
}
