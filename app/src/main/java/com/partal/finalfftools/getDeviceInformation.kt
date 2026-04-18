package com.partal.finalfftools

import android.os.Build

fun getDeviceInformation(): Map<String, String> {
    val androidVersion = Build.VERSION.RELEASE ?: "Unknown"

    val hardware = (1..4)
        .map { ('A'..'Z') + ('0'..'9') }
        .flatten()
        .shuffled()
        .take(4)
        .joinToString("")

    val year = (2013..2024).random()

    val deviceModel = Build.MODEL ?: "Unknown"

    val product = (10000..99999).random().toString()

    val sdkVersion = Build.VERSION.SDK_INT.toString()

    return mapOf(
        "Version" to androidVersion,
        "Hardware" to hardware,
        "Device" to year.toString(),
        "Device Model" to deviceModel,
        "Product" to product,
        "SDK" to sdkVersion
    )
}
