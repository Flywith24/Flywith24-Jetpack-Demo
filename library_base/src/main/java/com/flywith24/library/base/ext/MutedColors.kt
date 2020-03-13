package com.flywith24.library.base.ext

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color

object MutedColors {
    private val mutedColors = intArrayOf(
        Color.parseColor("#98FB98"),
        Color.parseColor("#FFEC8B"),
        Color.parseColor("#FFA500"),
        Color.parseColor("#B4EEB4"),
        Color.parseColor("#87CEFF")
    )

    private val darkerMutedColors = intArrayOf(
        Color.parseColor("#304233"),
        Color.parseColor("#353b45"),
        Color.parseColor("#392e3a"),
        Color.parseColor("#3e2a2a"),
        Color.parseColor("#474747")
    )

    fun colorAt(isDark: Boolean, index: Int) = palette(isDark).circular(index)

    fun random(isDark: Boolean): Int = palette(isDark).random()

    private fun palette(isDark: Boolean): IntArray = when (isDark) {
        true -> darkerMutedColors
        else -> mutedColors
    }
}

private fun IntArray.circular(index: Int) = this[index % size]

private fun IntArray.random() = this[(Math.random() * size).toInt()]

val Context.isDarkTheme
    get() = when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_NO,
        Configuration.UI_MODE_NIGHT_UNDEFINED -> false
        else -> true
    }