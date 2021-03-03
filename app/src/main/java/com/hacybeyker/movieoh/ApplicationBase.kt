package com.hacybeyker.movieoh

import android.app.Application
import com.hacybeyker.movieoh.di.viewModelModule
import com.hacybeyker.repository.network.di.API_KEY
import com.hacybeyker.repository.network.di.BASE_URL
import com.hacybeyker.repository.network.di.networkModule
import com.hacybeyker.repository.network.di.retrofitModule
import com.hacybeyker.usecases.di.useCaseModule
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ApplicationBase : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@ApplicationBase)
            modules(
                listOf(
                    retrofitModule,
                    useCaseModule,
                    networkModule,
                    viewModelModule
                )
            )
        }
        getKoin().run {
            setProperty(BASE_URL, BuildConfig.BASE_URL)
            setProperty(API_KEY, BuildConfig.API_KEY)
        }
    }
}