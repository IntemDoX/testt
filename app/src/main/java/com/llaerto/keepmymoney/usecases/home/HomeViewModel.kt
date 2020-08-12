package com.llaerto.keepmymoney.usecases.home

import com.llaerto.keepmymoney.base.BaseViewModel
import com.llaerto.keepmymoney.model.Category
import com.llaerto.keepmymoney.model.Record
import com.llaerto.keepmymoney.model.TabInfo
import java.util.*

class HomeViewModel() : BaseViewModel<HomeState, HomeActions>() {

    override fun initialState() = HomeState.InitTabsState(getTestData())

    fun getInfoForTab(position: Int) {
        stateData.value = HomeState.UpdateTabInfoState(getTestData()[position])
    }

    private fun getTestData() : List<TabInfo>{
        //todo remove it after implementation real data
        val list1 = mutableListOf(
            Record(0, true, Date().time, Category(0, "#BC0606", "food"), 22.00, "$", "some comment"),
            Record(1, false, Date().time, Category(1, "#2CB632", "car"), 33.99, "$", null),
            Record(2, false, Date().time, Category(2, "#757575", "house"), 4.49, "$", null),
            Record(4, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(3, true, Date().time, Category(3, "#212121", "medicine"), 123.11, "$", null),
            Record(3, true, Date().time, Category(3451, "#212121", "medicine"), 1208903.11, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(5, true, Date().time, Category(5, "#607D8B", "baby"), 23412.00, "$", null)
        )
        val list2 = mutableListOf(
            Record(0, true, Date().time, Category(0, "#BC0606", "food"), 22.00, "$", "some comment"),
            Record(1, false, Date().time, Category(1, "#2CB632", "car"), 33.99, "$", null)
        )

        val list3 = mutableListOf(
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(3, true, Date().time, Category(3, "#212121", "medicine"), 123.11, "$", null),
            Record(3, true, Date().time, Category(3451, "#212121", "medicine"), 1208903.11, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(5, true, Date().time, Category(5, "#607D8B", "baby"), 23412.00, "$", null)
        )

        val list4 = mutableListOf(
            Record(4, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(3, true, Date().time, Category(3451, "#212121", "medicine"), 1208903.11, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(5, true, Date().time, Category(5, "#607D8B", "baby"), 23412.00, "$", null)
        )
        val list5 = mutableListOf(
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(5, true, Date().time, Category(5, "#607D8B", "baby"), 23412.00, "$", null)
        )

        val list6 = mutableListOf(
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(3, true, Date().time, Category(3451, "#212121", "medicine"), 1208903.11, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(6, false, Date().time, Category(4, "#009688", "fun"), 931.00, "$", null),
            Record(5, true, Date().time, Category(5, "#607D8B", "baby"), 23412.00, "$", null)
        )

        return mutableListOf(
            TabInfo("feb", 20000.0, list1),
            TabInfo("mar", 30000.0, list2),
            TabInfo("apr", 40000.0, list3),
            TabInfo("may", 50000.0, list4),
            TabInfo("jun", 60000.0, list5),
            TabInfo("jul", 70000.0, list6)
        )
    }
}
