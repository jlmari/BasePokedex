package com.jlmari.android.basepokedex.memorydatasource.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemonDetail")
class PokemonDetailDbModel(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "order")
    val order: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "weight")
    val weightKg: Double,
    @ColumnInfo(name = "height")
    val heightMeters: Double,
    @ColumnInfo(name = "types")
    val types: List<String>,
    @ColumnInfo(name = "backPhoto")
    val backPhotoUrl: String,
    @ColumnInfo(name = "frontPhoto")
    val frontPhotoUrl: String,
    @ColumnInfo(name = "abilities")
    val abilities: List<String>,
    @ColumnInfo(name = "moves")
    val moves: List<String>,
    @ColumnInfo(name = "storedTime")
    val storedTime: Long
)