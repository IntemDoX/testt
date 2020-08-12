package com.llaerto.keepmymoney.usecases.home

import com.llaerto.keepmymoney.base.BaseViewAction
import com.llaerto.keepmymoney.base.BaseViewState
import com.llaerto.keepmymoney.model.TabInfo

sealed class HomeState : BaseViewState {
    object EmptyState : HomeState()
    class UpdateTabInfoState(var tabInfo: TabInfo) : HomeState()
    class InitTabsState(var tabsList: List<TabInfo>) : HomeState()
}

sealed class HomeActions : BaseViewAction