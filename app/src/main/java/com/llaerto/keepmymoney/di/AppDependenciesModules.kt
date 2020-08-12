package com.llaerto.keepmymoney.di

import com.llaerto.keepmymoney.utils.IntentActions
import com.llaerto.keepmymoney.utils.IntentActionsImpl
import org.koin.dsl.module

val appModule = module {
    single<IntentActions> { IntentActionsImpl(get()) }
}