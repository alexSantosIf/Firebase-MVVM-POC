package br.com.alex.firebasemvvmpoc

import android.app.Application
import br.com.alex.firebasemvvmpoc.data.service.MainService
import br.com.alex.firebasemvvmpoc.di.KoinModule.serviceModule
import br.com.alex.firebasemvvmpoc.di.KoinModule.viewModelModule
import br.com.alex.firebasemvvmpoc.view.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(viewModelModule, serviceModule))
        }
    }
}