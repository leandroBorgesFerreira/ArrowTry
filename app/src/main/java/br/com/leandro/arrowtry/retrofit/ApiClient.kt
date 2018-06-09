package br.com.leandro.arrowtry.retrofit

import br.com.leandro.arrowtry.superHeroes.dto.GithubAnswerDto
import br.com.leandro.arrowtry.superHeroes.dto.MarvelResponse
import com.karumi.marvelapiclient.model.CharacterDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("v1/public/characters")
    fun characters(@Query("ts") timestamp: String,
                   @Query("limit") limit: Int,
                   @Query("apikey") apiKey: String,
                   @Query("hash") hash: String) : Call<MarvelResponse<CharacterDto>>

    @GET("search/repositories")
    abstract fun getRepositories(@Query("q") language: String,
                                 @Query("sort") order: String,
                                 @Query("page") page: Int): Call<GithubAnswerDto>
}

fun apiClient() : ApiClient = retrofit().create(ApiClient::class.java)
