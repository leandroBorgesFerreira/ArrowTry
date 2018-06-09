package br.com.leandro.arrowtry.githubrepos.dto

import br.com.leandro.arrowtry.githubrepos.domain.Repository

class GithubAnswerDto(val total_count: Long,
                      val isIncomplete_results: Boolean,
                      val items: List<Repository>)
