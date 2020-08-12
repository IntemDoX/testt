package com.llaerto.keepmymoney.utils

import androidx.recyclerview.widget.DiffUtil
import com.llaerto.keepmymoney.model.RecordRow

class DiffCallbackRecords(private var newRecords: List<RecordRow>, private var oldRecords: MutableList<RecordRow>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newRecords[newItemPosition].id == oldRecords[oldItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldRecords.size
    }

    override fun getNewListSize(): Int {
        return newRecords.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newRecords[newItemPosition] == oldRecords[oldItemPosition]
    }
}