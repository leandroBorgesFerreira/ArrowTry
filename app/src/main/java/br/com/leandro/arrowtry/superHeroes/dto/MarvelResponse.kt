package br.com.leandro.arrowtry.superHeroes.dto

import com.google.gson.annotations.SerializedName

data class MarvelResponse<T>(
        @SerializedName("code") val code: Int,
        @SerializedName("status") val status: String,
        @SerializedName("copyright") val copyright: String,
        @SerializedName("attributionText") val attributionText: String,
        @SerializedName("attributionHTML") val getAttributionHtml: String,
        @SerializedName("etag") val etag: String,
        @SerializedName("data") val response: DataDto<T>
)
