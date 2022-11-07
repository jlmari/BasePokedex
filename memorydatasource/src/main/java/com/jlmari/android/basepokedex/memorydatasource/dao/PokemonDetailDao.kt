package com.jlmari.android.basepokedex.memorydatasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlmari.android.basepokedex.memorydatasource.models.PokemonDetailDbModel

@Dao
interface PokemonDetailDao {

    @Query("SELECT * FROM pokemonDetail WHERE id == :id")
    suspend fun getPokemonDetail(id: Int): PokemonDetailDbModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonDetail(pokemonDetailDbModel: PokemonDetailDbModel)

    @Query("DELETE FROM pokemonDetail WHERE id = :id")
    suspend fun deletePokemonDetail(id: String)
}
