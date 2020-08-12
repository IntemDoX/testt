package com.llaerto.keepmymoney.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

interface IntentActions {
    fun openContent(content: String)
}

class IntentActionsImpl(private val appContext: Context) : IntentActions {

    override fun openContent(content: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(content)
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        appContext.startActivity(intent)
    }
}