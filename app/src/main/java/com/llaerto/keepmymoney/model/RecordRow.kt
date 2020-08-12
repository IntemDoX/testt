package com.llaerto.keepmymoney.model

data class RecordRow(
    var id: Int,
    var isIncome: Boolean,
    var date: String,
    var category: Category,
    var count: String,
    var comment: String? = null
)