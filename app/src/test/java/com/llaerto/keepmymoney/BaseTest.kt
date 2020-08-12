package com.llaerto.keepmymoney

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.llaerto.keepmymoney.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
open class BaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()
}