package br.com.leandro.arrowtry.githubrepos.presentation

import arrow.data.Reader
import arrow.data.ReaderApi
import arrow.data.flatMap
import arrow.data.map
import arrow.syntax.function.pipe
import br.com.leandro.arrowtry.githubrepos.data.repositoryUseCase
import br.com.leandro.arrowtry.githubrepos.di.RepositoriesDeps
import br.com.leandro.arrowtry.githubrepos.domain.Repository
import br.com.leandro.arrowtry.githubrepos.view.RepositoriesView

fun getRepositories(): Reader<RepositoriesDeps, Unit> =
        ReaderApi.ask<RepositoriesDeps>()
            .flatMap({ (view, _) ->
                 repositoryUseCase()
                     .map({ io ->
                              io.unsafeRunAsync { reposEither ->
                                  reposEither.fold(
                                          { view.showGenericError() },
                                          { reposList -> drawRepos(reposList, view) })
                              }
                          })

             })

private fun drawRepos(repositoryList: List<Repository>, view: RepositoriesView) {
    repositoryList.sortedByDescending { repository -> repository.stargazersCount } pipe {
        view.drawHeroes(it)
    }
}
