package br.com.leandro.arrowtry.githubrepos.di

import br.com.leandro.arrowtry.githubrepos.view.RepositoriesView
import br.com.leandro.arrowtry.retrofit.ApiClient

data class RepositoriesDeps(val view: RepositoriesView,
                            val apiClient: ApiClient)
