package com.llaerto.keepmymoney.utils

import android.graphics.Point
import android.view.Display
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import com.google.android.material.tabs.TabLayout
import com.llaerto.keepmymoney.databinding.TabViewBinding

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun View.setInvisible() {
    visibility = View.INVISIBLE
}

fun View.setGone() {
    visibility = View.GONE
}

fun View.setVisibleOrGone(visible: Boolean) {
    if (visible) {
        setVisible()
    } else {
        setGone()
    }
}

fun TabLayout.Tab.setCustomTabData(binding: TabViewBinding, display: Display, name: String): TabLayout.Tab {
    customView = binding.root
    val size = Point()
    display.getSize(size)
    val width = size.x / 3
    binding.container.minWidth = width
    binding.container.maxWidth = width
    binding.title.text = name
    return this
}

fun TabLayout.createCustomTab(binding: TabViewBinding, display: Display, name: String) {
    addTab(newTab().setCustomTabData(binding, display, name))
}

fun TabLayout.scrollToTab(tabIndex: Int) {
    if (viewTreeObserver.isAlive) {
        viewTreeObserver.dispatchOnGlobalLayout()
        viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                getTabAt(tabIndex)!!.select()
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}
