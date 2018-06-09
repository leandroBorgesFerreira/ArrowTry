package br.com.leandro.arrowtry.superHeroes.di

import br.com.leandro.arrowtry.superHeroes.view.RepositoriesView

sealed class RepositoriesDeps(view: RepositoriesView) {
    data class RepositoriesContext(val view: RepositoriesView) : RepositoriesDeps(view)
}
