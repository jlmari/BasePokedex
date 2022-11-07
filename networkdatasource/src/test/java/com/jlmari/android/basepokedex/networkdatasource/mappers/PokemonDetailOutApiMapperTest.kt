package com.jlmari.android.basepokedex.networkdatasource.mappers

import com.jlmari.android.basepokedex.domain.models.PokemonDetailModel
import com.jlmari.android.basepokedex.networkdatasource.models.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.util.*

@RunWith(Parameterized::class)
internal class PokemonDetailOutApiMapperTest(
    private val input: GetPokemonDetailResponseApiModel,
    private val expected: PokemonDetailModel
) {

    private val pokemonDetailOutApiMapper = PokemonDetailOutApiMapper()

    @Test
    fun `Return expected PokemonDetailModel attributes when map operation invoked with input GetPokemonDetailResponseApiModel`() {
        // Given each collection in Companion.data()

        val result = pokemonDetailOutApiMapper.map(input)

        Assert.assertEquals(expected, result)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> = listOf(
            arrayOf(
                GetPokemonDetailResponseApiModel(
                    id = 1,
                    order = 1,
                    name = "pokemonName",
                    weight = 90,
                    height = 7,
                    typeList = listOf(
                        PokemonTypeApiModel(PokemonTypeInfoApiModel("type1", "")),
                        PokemonTypeApiModel(PokemonTypeInfoApiModel("type2", ""))
                    ),
                    sprites = PokemonSpritesApiModel(
                        "https://backPhotoUrl.png",
                        "https://frontPhotoUrl.png"
                    ),
                    abilityList = listOf(
                        PokemonAbilityApiModel(PokemonAbilityInfoApiModel("ability1", "")),
                        PokemonAbilityApiModel(PokemonAbilityInfoApiModel("ability2", "")),
                        PokemonAbilityApiModel(PokemonAbilityInfoApiModel("ability3", ""))
                    ),
                    moveList = listOf(
                        PokemonMoveApiModel(PokemonMoveInfoApiModel("move1", "")),
                        PokemonMoveApiModel(PokemonMoveInfoApiModel("move2", ""))
                    )
                ),
                PokemonDetailModel(
                    id = 1,
                    order = 1,
                    name = "pokemonName",
                    weightKg = 9.0,
                    heightMeters = 0.7,
                    types = listOf("type1", "type2"),
                    backPhotoUrl = "https://backPhotoUrl.png",
                    frontPhotoUrl = "https://frontPhotoUrl.png",
                    abilities = listOf("ability1", "ability2", "ability3"),
                    moves = listOf("move1", "move2"),
                )
            ),
            arrayOf(
                GetPokemonDetailResponseApiModel(
                    id = 32,
                    order = 56,
                    name = "pokemonName3",
                    weight = 123,
                    height = 53,
                    typeList = listOf(
                        PokemonTypeApiModel(PokemonTypeInfoApiModel("type3", "")),
                        PokemonTypeApiModel(PokemonTypeInfoApiModel("type7", ""))
                    ),
                    sprites = PokemonSpritesApiModel(
                        "https://backPhotoUrl.png",
                        "https://frontPhotoUrl.png"
                    ),
                    abilityList = listOf(
                        PokemonAbilityApiModel(PokemonAbilityInfoApiModel("ability4", "")),
                        PokemonAbilityApiModel(PokemonAbilityInfoApiModel("ability8", ""))
                    ),
                    moveList = emptyList()
                ),
                PokemonDetailModel(
                    id = 32,
                    order = 56,
                    name = "pokemonName3",
                    weightKg = 12.3,
                    heightMeters = 5.3,
                    types = listOf("type3", "type7"),
                    backPhotoUrl = "https://backPhotoUrl.png",
                    frontPhotoUrl = "https://frontPhotoUrl.png",
                    abilities = listOf("ability4", "ability8"),
                    moves = emptyList(),
                )
            )
        )
    }
}
