package br.com.leandro.arrowtry.githubrepos.data

import arrow.core.right
import arrow.effects.IO
import br.com.leandro.arrowtry.githubrepos.domain.Repository
import br.com.leandro.arrowtry.retrofit.apiClient
import kotlinx.coroutines.experimental.async

fun fetchAllRepositories(): IO<List<Repository>> =
        IO.async { either ->
            async {
                either(queryForRepositories().right())
            }
        }


private fun queryForRepositories(): List<Repository> =
        apiClient()
                .getRepositories("Java", "star", 1)
                .execute()
                .body()!!
                .items
