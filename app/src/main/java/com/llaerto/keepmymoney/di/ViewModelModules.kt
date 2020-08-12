package com.llaerto.keepmymoney.di

import com.llaerto.keepmymoney.usecases.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { HomeViewModel() }
}