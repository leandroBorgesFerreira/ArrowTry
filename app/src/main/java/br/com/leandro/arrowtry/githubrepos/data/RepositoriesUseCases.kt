package br.com.leandro.arrowtry.githubrepos.data

import arrow.effects.IO
import br.com.leandro.arrowtry.githubrepos.domain.Repository

fun repositoryUseCase() : IO<List<Repository>> = fetchAllRepositories()
