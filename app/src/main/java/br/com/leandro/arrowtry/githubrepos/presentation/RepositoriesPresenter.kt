package br.com.leandro.arrowtry.githubrepos.presentation

import arrow.data.Reader
import arrow.data.ReaderApi
import arrow.data.map
import br.com.leandro.arrowtry.githubrepos.data.repositoryUseCase
import br.com.leandro.arrowtry.githubrepos.di.RepositoriesDeps.RepositoriesContext
import br.com.leandro.arrowtry.githubrepos.domain.Repository
import br.com.leandro.arrowtry.githubrepos.view.RepositoriesView

fun getSuperHeroes() : Reader<RepositoriesContext, Unit> =
        ReaderApi.ask<RepositoriesContext>()
            .map({ (view) ->
                     repositoryUseCase()
                         .unsafeRunAsync { heroesEither ->
                             heroesEither.fold(
                                 { view.showGenericError() },
                                 { heroList -> drawHeroes(heroList, view) })
                         }
                 })

private fun drawHeroes(repositoryList: List<Repository>, view: RepositoriesView) {
    view.drawHeroes(repositoryList)
}
