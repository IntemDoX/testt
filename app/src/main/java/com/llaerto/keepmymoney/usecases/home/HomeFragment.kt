package com.llaerto.keepmymoney.usecases.home

import android.os.Bundle
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.llaerto.keepmymoney.R
import com.llaerto.keepmymoney.base.BaseFragment
import com.llaerto.keepmymoney.databinding.FragmentHomeBinding
import com.llaerto.keepmymoney.databinding.TabViewBinding
import com.llaerto.keepmymoney.model.RecordRow
import com.llaerto.keepmymoney.model.TabInfo
import com.llaerto.keepmymoney.model.toRecordRow
import com.llaerto.keepmymoney.utils.ItemDecorator
import com.llaerto.keepmymoney.utils.SwipeController
import com.llaerto.keepmymoney.utils.createCustomTab
import com.llaerto.keepmymoney.utils.scrollToTab
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<HomeState, HomeActions, HomeViewModel, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by viewModel()
    override var _binding: FragmentHomeBinding? = null
    private val adapter: HomeAdapter = HomeAdapter {
        onItemClick(it.id)
    }

    override fun applyViewState(viewState: HomeState) {
        when (viewState) {
            is HomeState.UpdateTabInfoState -> {
                val listOfROws: MutableList<RecordRow> = viewState.tabInfo.records.map { it.toRecordRow() }.toMutableList()
                adapter.setItems(listOfROws)
                binding.balance.text = viewState.tabInfo.balance.toString()
            }
            is HomeState.InitTabsState -> {
                initTabLayout(viewState.tabsList)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun onItemClick(id: Int) {
        //todo handle item click
    }

    private fun openTab(position: Int) {
        viewModel.getInfoForTab(position)
    }

    private fun initTabLayout(tabsList: List<TabInfo>) {
        val display: Display = requireActivity().windowManager.defaultDisplay
        for (tab in tabsList) {
            binding.tabLayout.createCustomTab(TabViewBinding.inflate(LayoutInflater.from(requireContext())), display, tab.name)
        }
        tabLayout.tabGravity = if (tabsList.size == 1) TabLayout.GRAVITY_CENTER else TabLayout.GRAVITY_START
        binding.tabLayout.scrollToTab(tabsList.size - 1)
        binding.tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.run {
                    openTab(position)
                }
            }
        })
    }

    private fun initRecyclerView() {
        binding.recordList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recordList.addItemDecoration(ItemDecorator())
        binding.recordList.adapter = adapter
        val swipeHelper = SwipeController { position ->
            val recordToRemove = adapter.getItemByPosition(position)
            adapter.removeItem(position)
            val snackbar = Snackbar
                .make(binding.recordList, getString(R.string.snack_item_removed), Snackbar.LENGTH_LONG)
            snackbar.setAction(getString(R.string.snack_undo)) {
                adapter.restoreItem(recordToRemove, position)
            }
            snackbar.setActionTextColor(requireContext().getColor(R.color.colorAccent))
            snackbar.show()
        }
        swipeHelper.attachToRecyclerView(binding.recordList)
    }
}