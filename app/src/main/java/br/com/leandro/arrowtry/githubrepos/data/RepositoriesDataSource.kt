package br.com.leandro.arrowtry.githubrepos.data

import arrow.core.Try
import arrow.data.Reader
import arrow.data.ReaderApi
import arrow.data.map
import arrow.effects.IO
import br.com.leandro.arrowtry.githubrepos.di.RepositoriesDeps
import br.com.leandro.arrowtry.githubrepos.domain.Repository
import br.com.leandro.arrowtry.retrofit.ApiClient
import kotlinx.coroutines.experimental.async

fun fetchAllRepositories(): Reader<RepositoriesDeps, IO<List<Repository>>> =
        ReaderApi.ask<RepositoriesDeps>()
                .map({ deps ->
                         IO.async<List<Repository>> { either ->
                             async {
                                 either(queryForRepositories(deps.apiClient).toEither())
                             }
                         }
                     })


private fun queryForRepositories(apiClient: ApiClient): Try<List<Repository>> =
        Try {
            apiClient
                    .getRepositories("Java", "star", 1)
                    .execute()
                    .body()!!
                    .items
        }
