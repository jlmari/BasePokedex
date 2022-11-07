package com.jlmari.android.basepokedex.memorydatasource.mappers

import com.jlmari.android.basepokedex.domain.models.PokemonDetailModel
import com.jlmari.android.basepokedex.domain.utils.Mapper
import com.jlmari.android.basepokedex.memorydatasource.models.PokemonDetailDbModel
import java.util.*
import javax.inject.Inject

class PokemonDetailInDbMapper @Inject constructor() :
    Mapper<PokemonDetailModel, PokemonDetailDbModel> {

    override fun map(from: PokemonDetailModel): PokemonDetailDbModel =
        PokemonDetailDbModel(
            from.id,
            from.order,
            from.name,
            from.weightKg,
            from.heightMeters,
            from.types,
            from.backPhotoUrl,
            from.frontPhotoUrl,
            from.abilities,
            from.moves,
            Date().time
        )
}
