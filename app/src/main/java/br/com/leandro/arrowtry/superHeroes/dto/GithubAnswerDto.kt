package br.com.leandro.arrowtry.superHeroes.dto

import br.com.leandro.arrowtry.superHeroes.domain.Repository

class GithubAnswerDto(val total_count: Long,
                      val isIncomplete_results: Boolean,
                      val items: List<Repository>)
