package com.llaerto.keepmymoney.usecases.home

import com.llaerto.keepmymoney.BaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest : BaseTest() {

    @Test
    fun `initial state of splash viewmodel is empty state`() {
        val vm = buildViewModel()

        assert(vm.stateData.value is HomeState.EmptyState)
    }

    private fun buildViewModel(): HomeViewModel {
        return HomeViewModel()
    }
}