package com.jlmari.android.basepokedex.networkdatasource.mappers

import com.jlmari.android.basepokedex.domain.models.PokemonModel
import com.jlmari.android.basepokedex.networkdatasource.models.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
internal class PokemonListOutApiMapperTest(
    private val input: List<PokemonApiModel>,
    private val expected: List<PokemonModel>
) {

    private val pokemonListOutApiMapper = PokemonListOutApiMapper()

    @Test
    fun `Return expected List of PokemonModel when map operation invoked with input List of PokemonApiModel`() {
        // Given each collection in Companion.data()

        val result = pokemonListOutApiMapper.map(input)

        Assert.assertEquals(expected, result)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> = listOf(
            arrayOf(
                listOf(
                    PokemonApiModel(
                        name = "bulbasaur",
                        detailUrl = "https://pokeapi.co/api/v2/pokemon/1"
                    ),
                    PokemonApiModel(
                        name = "ivysaur",
                        detailUrl = "https://pokeapi.co/api/v2/pokemon/2"
                    ),
                    PokemonApiModel(
                        name = "venusaur",
                        detailUrl = "https://pokeapi.co/api/v2/pokemon/3"
                    ),
                ),
                listOf(
                    PokemonModel(
                        name = "bulbasaur",
                        id = 1
                    ),
                    PokemonModel(
                        name = "ivysaur",
                        id = 2
                    ),
                    PokemonModel(
                        name = "venusaur",
                        id = 3
                    )
                )
            ),
            arrayOf(
                listOf(
                    PokemonApiModel(
                        name = "charmander",
                        detailUrl = "https://pokeapi.co/api/v2/pokemon/4"
                    ),
                    PokemonApiModel(
                        name = "charmeleon",
                        detailUrl = "https://pokeapi.co/api/v2/pokemon/5"
                    ),
                    PokemonApiModel(
                        name = "charizard",
                        detailUrl = "https://pokeapi.co/api/v2/pokemon/6"
                    ),
                ),
                listOf(
                    PokemonModel(
                        name = "charmander",
                        id = 4
                    ),
                    PokemonModel(
                        name = "charmeleon",
                        id = 5
                    ),
                    PokemonModel(
                        name = "charizard",
                        id = 6
                    )
                )
            )
        )
    }
}
