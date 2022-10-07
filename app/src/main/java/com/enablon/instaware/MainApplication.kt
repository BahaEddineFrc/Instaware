package com.enablon.instaware

import android.app.Application
import com.enablon.instaware.common.utils.logi
import com.enablon.instaware.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class MainApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        configureDI()
    }

    /**
     * Configure dependency injection library (Koin)
     *
     */
    private fun configureDI() {
        logi{"Koin DI configuration"}
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                AppModule.networkModule,
                AppModule.repositoryModule,
                AppModule.viewModelModule,
                AppModule.useCasesModule,
                AppModule.adapterModule
            )
        }
    }
}