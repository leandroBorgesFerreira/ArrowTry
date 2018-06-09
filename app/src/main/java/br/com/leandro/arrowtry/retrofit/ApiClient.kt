package br.com.leandro.arrowtry.retrofit

import br.com.leandro.arrowtry.githubrepos.dto.GithubAnswerDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("search/repositories")
    abstract fun getRepositories(@Query("q") language: String,
                                 @Query("sort") order: String,
                                 @Query("page") page: Int): Call<GithubAnswerDto>
}

fun apiClient() : ApiClient = retrofit().create(ApiClient::class.java)
