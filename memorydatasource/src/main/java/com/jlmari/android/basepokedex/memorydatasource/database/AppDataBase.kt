package com.jlmari.android.basepokedex.memorydatasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jlmari.android.basepokedex.memorydatasource.converters.Converters
import com.jlmari.android.basepokedex.memorydatasource.dao.PokemonDetailDao
import com.jlmari.android.basepokedex.memorydatasource.models.PokemonDetailDbModel

@Database(version = 1, entities = [PokemonDetailDbModel::class])
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun pokemonDetailDao(): PokemonDetailDao

    companion object {

        // Create Singleton instance of AppDataBase
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
            }
        }

        private fun buildDatabase(context: Context): AppDataBase =
            Room.databaseBuilder(context, AppDataBase::class.java, "base-pokedex-db")
                .fallbackToDestructiveMigration()
                .build()
    }
}
