package com.jlmari.android.basepokedex.memorydatasource

import com.jlmari.android.basepokedex.data.datasources.MemoryDataSource
import com.jlmari.android.basepokedex.domain.models.ErrorModel
import com.jlmari.android.basepokedex.domain.models.PokemonDetailModel
import com.jlmari.android.basepokedex.domain.utils.Failure
import com.jlmari.android.basepokedex.domain.utils.Response
import com.jlmari.android.basepokedex.domain.utils.Success
import com.jlmari.android.basepokedex.memorydatasource.cache.Cache
import com.jlmari.android.basepokedex.memorydatasource.dao.PokemonDetailDao
import com.jlmari.android.basepokedex.memorydatasource.mappers.PokemonDetailInDbMapper
import com.jlmari.android.basepokedex.memorydatasource.mappers.PokemonDetailOutDbMapper
import javax.inject.Inject

class MemoryDataSourceImpl @Inject constructor(
    private val pokemonDetailDao: PokemonDetailDao,
    private val pokemonDetailOutDbMapper: PokemonDetailOutDbMapper,
    private val pokemonDetailInDbMapper: PokemonDetailInDbMapper
) : MemoryDataSource {

    override suspend fun getPokemonDetail(id: Int): Response<PokemonDetailModel, ErrorModel> =
        pokemonDetailDao.getPokemonDetail(id)?.let { pokemonDetailDb ->
            Cache.checkTimestampCache(pokemonDetailDb.storedTime, pokemonDetailOutDbMapper.map(pokemonDetailDb))
        } ?: run { Failure(ErrorModel("db error")) }

    override suspend fun savePokemonDetail(pokemonDetail: PokemonDetailModel): Response<Unit, ErrorModel> =
        Success(pokemonDetailDao.insertPokemonDetail(pokemonDetailInDbMapper.map(pokemonDetail)))
}
