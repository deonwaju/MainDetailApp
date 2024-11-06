package com.deontch.core.common.util

import android.content.Context
import android.content.pm.PackageManager
import timber.log.Timber

fun getAppVersionName(context: Context): String {
    var versionName = ""
    try {
        val info = context.packageManager?.getPackageInfo(context.packageName, 0)
        versionName = info?.versionName ?: ""
    } catch (e: PackageManager.NameNotFoundException) {
        Timber.e(e.message)
    }
    return versionName
}

fun Int.toMonetaryString(): String = "£$this"
