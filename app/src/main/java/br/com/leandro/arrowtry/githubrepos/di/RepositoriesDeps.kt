package br.com.leandro.arrowtry.githubrepos.di

import br.com.leandro.arrowtry.githubrepos.view.RepositoriesView

sealed class RepositoriesDeps(view: RepositoriesView) {
    data class RepositoriesContext(val view: RepositoriesView) : RepositoriesDeps(view)
}
