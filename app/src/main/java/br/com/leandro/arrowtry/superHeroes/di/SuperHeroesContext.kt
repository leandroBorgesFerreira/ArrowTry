package br.com.leandro.arrowtry.superHeroes.di

import android.content.Context
import br.com.leandro.arrowtry.superHeroes.view.HeroesView

sealed class SuperHeroesContext(ctx: Context) {
    data class GetHeroesContext(val ctx: Context, val view: HeroesView) : SuperHeroesContext(ctx)
}
