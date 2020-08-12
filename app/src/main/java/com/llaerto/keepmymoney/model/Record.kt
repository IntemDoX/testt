package com.llaerto.keepmymoney.model

import com.llaerto.keepmymoney.utils.toShortDate

data class Record(
    var id: Int,
    var isIncome: Boolean,
    var date: Long,
    var category: Category,
    var count: Double,
    var currency: String,
    var comment: String? = null
)

fun Record.toRecordRow(): RecordRow {
    return RecordRow(
        id = id,
        isIncome = isIncome,
        date = date.toShortDate(),
        category = category,
        count = count.toString(),
        comment = comment
    )
}