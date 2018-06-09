package br.com.leandro.arrowtry.githubrepos.domain

import com.google.gson.annotations.SerializedName

class Repository(val id: Long,
                 val name: String,
                 val description: String,
                 val owner: User,
                 @SerializedName("forks_count") val forksCount: Int,
                 @SerializedName("stargazers_count") val stargazersCount: Int)
