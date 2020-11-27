package com.test.redditproject.util

import android.content.Intent
import android.net.Uri
import android.text.style.ClickableSpan
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent

const val CHROME_PACKAGE = "com.android.chrome"

fun String.makeClickableSpan(): ClickableSpan {
    return object : ClickableSpan() {
        override fun onClick(view: View) {
            val builder = CustomTabsIntent.Builder()
            builder.addDefaultShareMenuItem()
            builder.setShowTitle(true)
            builder.addDefaultShareMenuItem()
            val customTabsIntent = builder.build()
            customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            customTabsIntent.intent.setPackage(CHROME_PACKAGE)
            customTabsIntent.launchUrl(view.context, Uri.parse(this@makeClickableSpan))
        }
    }
}