package br.com.leandro.arrowtry.githubrepos.data

import arrow.core.Try
import arrow.effects.IO
import br.com.leandro.arrowtry.githubrepos.domain.Repository
import br.com.leandro.arrowtry.retrofit.apiClient
import kotlinx.coroutines.experimental.async

fun fetchAllRepositories(): IO<List<Repository>> =
        IO.async { either ->
            async {
                either(queryForRepositories().toEither())
            }
        }


private fun queryForRepositories(): Try<List<Repository>> =
        Try {
            apiClient()
                    .getRepositories("Java", "star", 1)
                    .execute()
                    .body()!!
                    .items
        }
