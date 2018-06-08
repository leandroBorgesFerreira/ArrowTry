package br.com.leandro.arrowtry.application

import android.app.Application
import br.com.leandro.arrowtry.retrofit.initRetrofit

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initRetrofit(this)
    }
}
