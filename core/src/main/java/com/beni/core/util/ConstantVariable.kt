package com.beni.core.util

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.beni.core.data.local.preferences.CanvasSize

object ConstantVariable {
    const val TAG = "Response::::::::::"
    const val PREF_NAME = "sizePreference"
    const val ENTITY_NAME = "PointEntity"

    val KEY_WIDTH = intPreferencesKey("width")
    val KEY_HEIGHT = intPreferencesKey("height")
}