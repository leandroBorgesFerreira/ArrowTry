package br.com.leandro.arrowtry.superHeroes.data

import arrow.effects.IO
import arrow.effects.fix
import arrow.effects.monadDefer
import arrow.typeclasses.binding
import br.com.leandro.arrowtry.retrofit.apiClient
import br.com.leandro.arrowtry.superHeroes.domain.Repository
import kotlinx.coroutines.experimental.async

fun fetchAllRepositories(): IO<List<Repository>> =
                         IO.monadDefer().binding {
                             val result = IO.async<List<Repository>> {
                                 async {
                                     queryForRepositories()
                                 }
                             }.bind()
                             
                         }.fix()

private fun queryForRepositories(): List<Repository> =
        apiClient()
                .getRepositories("Java", "start", 1)
                .execute()
                .body()!!
                .items
