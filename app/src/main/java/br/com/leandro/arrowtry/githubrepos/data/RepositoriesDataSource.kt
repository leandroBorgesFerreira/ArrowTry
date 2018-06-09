package br.com.leandro.arrowtry.githubrepos.data

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
import br.com.leandro.arrowtry.githubrepos.domain.Repository
import br.com.leandro.arrowtry.retrofit.apiClient
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

fun fetchAllRepositories(): IO<List<Repository>> =
                         IO.monadDefer().binding {
                             val result = runInAsyncContext(
                                     f = { queryForRepositories() },
                                     onError = {
                                         it.printStackTrace()
                                         IO.raiseError<List<Repository>>(it)
                                     },
                                     onSuccess = { IO.just(it) },
                                     AC = IO.async()
                             ).bind()

                             result.bind()
                         }.fix()

private fun queryForRepositories(): List<Repository> =
        apiClient()
                .getRepositories("Java", "start", 1)
                .execute()
                .body()!!
                .items

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
