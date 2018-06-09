package br.com.leandro.arrowtry.superHeroes.view

import br.com.leandro.arrowtry.superHeroes.domain.Repository

interface RepositoriesView {
    fun showNotFoundError()
    fun showGenericError()
    fun showAuthenticationError()
    fun drawHeroes(heroes: List<Repository>)
}
