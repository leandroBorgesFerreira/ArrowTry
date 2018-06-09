package br.com.leandro.arrowtry.superHeroes.domain

import com.google.gson.annotations.SerializedName

class User(val id: Long,
           @SerializedName("avatar_url") val avatarUrl: String,
           val login: String)
