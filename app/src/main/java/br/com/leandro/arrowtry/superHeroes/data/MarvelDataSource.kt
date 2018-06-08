package br.com.leandro.arrowtry.superHeroes.data

import arrow.Kind
import arrow.core.Try
import arrow.core.right
import arrow.effects.IO
import arrow.effects.async
import arrow.effects.fix
import arrow.effects.monadDefer
import arrow.effects.typeclasses.Async
import arrow.syntax.function.pipe
import arrow.typeclasses.binding
import br.com.leandro.arrowtry.retrofit.apiClient
import br.com.leandro.arrowtry.superHeroes.utils.privateKey
import br.com.leandro.arrowtry.superHeroes.utils.publicKey
import br.com.leandro.arrowtry.utils.apiHash
import br.com.leandro.arrowtry.utils.timestamp
import com.karumi.marvelapiclient.model.CharacterDto
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

fun fetchAllHeroes(): IO<List<CharacterDto>> =
                         IO.monadDefer().binding {
                             val result = runInAsyncContext(
                                     f = { queryForHeroes() },
                                     onError = {
                                         it.printStackTrace()
                                         IO.raiseError<List<CharacterDto>>(it)
                                     },
                                     onSuccess = { IO.just(it) },
                                     AC = IO.async()
                             ).bind()
                             result.bind()
                         }.fix()

private fun queryForHeroes(): List<CharacterDto> =
        timestamp() pipe { ts ->
            apiClient()
                    .characters(ts, 30, publicKey, apiHash(ts, publicKey, privateKey))
                    .execute()
                    .body()!!
                    .response
                    .results
        }

/**
 * Just syntax to improve readability.
 */
private fun <F, A, B> runInAsyncContext(
        f: () -> A,
        onError: (Throwable) -> B,
        onSuccess: (A) -> B,
        AC: Async<F>
): Kind<F, B> {
    return AC.async { proc ->
        async(CommonPool) {
            Try { f() }.fold(onError, onSuccess) pipe { result ->
                proc(result.right())
            }
        }
    }
}
