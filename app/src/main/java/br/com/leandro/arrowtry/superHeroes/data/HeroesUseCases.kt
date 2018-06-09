package br.com.leandro.arrowtry.superHeroes.data

import arrow.effects.IO
import br.com.leandro.arrowtry.superHeroes.domain.Repository

fun repositoryUseCase() : IO<List<Repository>> = fetchAllRepositories()
