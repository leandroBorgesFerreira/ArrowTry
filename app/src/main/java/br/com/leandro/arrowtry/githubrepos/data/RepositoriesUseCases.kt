package br.com.leandro.arrowtry.githubrepos.data

import arrow.data.Reader
import arrow.effects.IO
import br.com.leandro.arrowtry.githubrepos.di.RepositoriesDeps
import br.com.leandro.arrowtry.githubrepos.domain.Repository

fun repositoryUseCase() : Reader<RepositoriesDeps, IO<List<Repository>>> = fetchAllRepositories()
