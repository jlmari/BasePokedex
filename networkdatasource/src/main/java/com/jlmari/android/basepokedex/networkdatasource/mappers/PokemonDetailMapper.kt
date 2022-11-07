package com.jlmari.android.basepokedex.networkdatasource.mappers

import com.jlmari.android.basepokedex.domain.models.PokemonDetailModel
import com.jlmari.android.basepokedex.domain.utils.Mapper
import com.jlmari.android.basepokedex.networkdatasource.models.GetPokemonDetailResponseApiModel
import javax.inject.Inject

class PokemonDetailMapper @Inject constructor() :
    Mapper<GetPokemonDetailResponseApiModel, PokemonDetailModel> {

    override fun map(from: GetPokemonDetailResponseApiModel): PokemonDetailModel =
        PokemonDetailModel(
            from.id,
            from.order,
            from.name,
            from.weight.toDouble() / 10,
            from.height.toDouble() / 10,
            from.typeList.map { it.typeInfo.name },
            from.photoList.backDefaultPhoto,
            from.photoList.frontDefaultPhoto,
            from.abilityList.map { it.abilityInfo.name },
            from.moveList.map { it.moveInfo.name }
        )
}
