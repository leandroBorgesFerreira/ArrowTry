package br.com.leandro.arrowtry.superHeroes.domain

import arrow.effects.IO
import br.com.leandro.arrowtry.superHeroes.data.fetchAllHeroes
import com.karumi.marvelapiclient.model.CharacterDto
import com.karumi.marvelapiclient.model.MarvelImage

fun getHeroesUseCase() : IO<List<CharacterDto>> = fetchAllHeroes().map { discardNonValidHeroes(it) }

private fun discardNonValidHeroes(heroes: List<CharacterDto>) =
    heroes.filter {
      it.thumbnail
              .getImageUrl(MarvelImage.Size.PORTRAIT_UNCANNY)
              .contains("image_not_available", true)
    }
