package br.com.leandro.arrowtry.retrofit

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

}

fun apiClient() : ApiClient = retrofit().create(ApiClient::class.java)
