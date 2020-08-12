package com.llaerto.keepmymoney.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.llaerto.keepmymoney.R

class ItemDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) == 0) {
            return
        }
        outRect.top = parent.context.resources.getDimensionPixelOffset(R.dimen.default_margin_xxs)
    }
}