package com.jlmari.android.basepokedex.domain.utils

/**
 * Interface to map a [List] of one [Object]s to a [List] of another.
 */
interface ListMapper<in FROM, out TO> : Mapper<FROM, TO> {

    fun map(fromList: List<FROM>): List<TO> = fromList.map(this::map)
}
