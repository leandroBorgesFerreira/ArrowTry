package br.com.leandro.arrowtry.superHeroes.presentation

import arrow.data.Reader
import arrow.data.ReaderApi
import arrow.data.map
import arrow.syntax.function.pipe
import br.com.leandro.arrowtry.superHeroes.di.SuperHeroesContext.GetHeroesContext
import br.com.leandro.arrowtry.superHeroes.domain.getHeroesUseCase
import br.com.leandro.arrowtry.superHeroes.view.HeroesView
import br.com.leandro.arrowtry.superHeroes.view.viewmodel.SuperHeroViewModel
import com.karumi.marvelapiclient.model.CharacterDto
import com.karumi.marvelapiclient.model.MarvelImage

fun getSuperHeroes() : Reader<GetHeroesContext, Unit> = ReaderApi.ask<GetHeroesContext>()
        .map({ (_, view) ->
                 getHeroesUseCase()
                     .unsafeRunAsync { heroesEither ->
                         heroesEither.fold(
                             { view.showGenericError() },
                             { heroList -> drawHeroes(heroList, view) })
                     }
             })

private fun drawHeroes(heroList: List<CharacterDto>, view: HeroesView) {
    heroList.map { parseHeroViewModel(it) } pipe {
        view.drawHeroes(it)
    }
}

private fun parseHeroViewModel(heroDto : CharacterDto) =
            SuperHeroViewModel(heroDto.id,
                               heroDto.name,
                               heroDto.thumbnail.getImageUrl(MarvelImage.Size.PORTRAIT_UNCANNY),
                               heroDto.description)
