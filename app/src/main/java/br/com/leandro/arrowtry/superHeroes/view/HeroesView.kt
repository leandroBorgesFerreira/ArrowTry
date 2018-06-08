package br.com.leandro.arrowtry.superHeroes.view

import br.com.leandro.arrowtry.superHeroes.view.viewmodel.SuperHeroViewModel

interface HeroesView {
    fun showNotFoundError()
    fun showGenericError()
    fun showAuthenticationError()
    fun drawHeroes(heroes: List<SuperHeroViewModel>)
}
