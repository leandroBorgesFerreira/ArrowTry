package br.com.leandro.arrowtry.githubrepos.view

import br.com.leandro.arrowtry.githubrepos.domain.Repository

interface RepositoriesView {
    fun showNotFoundError()
    fun showGenericError()
    fun showAuthenticationError()
    fun drawHeroes(heroes: List<Repository>)
}
