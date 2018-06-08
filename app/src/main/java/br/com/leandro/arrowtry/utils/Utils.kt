package br.com.leandro.arrowtry.utils

fun apiHash(timestamp: String, publicKey: String, privateKey: String) : String =
        AuthHashGenerator.generateHash(
                StringBuilder(timestamp)
                        .append(privateKey)
                        .append(publicKey)
                        .toString())

fun timestamp() : String = System.currentTimeMillis().toString()
