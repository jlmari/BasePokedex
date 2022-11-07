package com.jlmari.android.basepokedex.application.di

import android.content.Context
import com.jlmari.android.basepokedex.application.PokeApp
import com.jlmari.android.basepokedex.application.scopes.PerApplication
import com.jlmari.android.basepokedex.main.di.MainComponent
import com.jlmari.android.basepokedex.pokedex.di.PokedexComponent
import com.jlmari.android.basepokedex.pokemondetail.di.PokemonDetailComponent
import dagger.BindsInstance
import dagger.Component

@PerApplication
@Component(
    modules = [
        DispatchersModule::class,
        RepositoriesModule::class,
        DataSourcesModule::class,
        NetworkDataSourceModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appContext: Context): AppComponent
    }

    fun inject(app: PokeApp)

    val context: Context

    fun mainComponentFactory(): MainComponent.Factory

    fun pokedexComponentFactory(): PokedexComponent.Factory

    fun pokemonDetailFactory(): PokemonDetailComponent.Factory
}
