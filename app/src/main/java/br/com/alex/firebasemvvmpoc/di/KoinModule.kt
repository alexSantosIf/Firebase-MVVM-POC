package br.com.alex.firebasemvvmpoc.di

import br.com.alex.firebasemvvmpoc.data.service.MainService
import br.com.alex.firebasemvvmpoc.view.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModule {

    val viewModelModule =
        module {
            viewModel { MainViewModel() }
        }

    val serviceModule =
        module {
            single { MainService() }
        }
}