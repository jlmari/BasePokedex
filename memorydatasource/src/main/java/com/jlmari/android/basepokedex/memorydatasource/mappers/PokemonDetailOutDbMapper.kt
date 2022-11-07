package com.jlmari.android.basepokedex.memorydatasource.mappers

import com.jlmari.android.basepokedex.domain.models.PokemonDetailModel
import com.jlmari.android.basepokedex.domain.utils.Mapper
import com.jlmari.android.basepokedex.memorydatasource.models.PokemonDetailDbModel
import javax.inject.Inject

class PokemonDetailOutDbMapper @Inject constructor() :
    Mapper<PokemonDetailDbModel, PokemonDetailModel> {

    override fun map(from: PokemonDetailDbModel): PokemonDetailModel =
        PokemonDetailModel(
            from.id,
            from.order,
            from.name,
            from.weightKg,
            from.heightMeters,
            from.types,
            from.backPhotoUrl,
            from.frontPhotoUrl,
            from.abilities,
            from.moves
        )
}
