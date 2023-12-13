package com.beni.core.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.beni.core.util.ConstantVariable
import com.beni.core.util.ConstantVariable.KEY_HEIGHT
import com.beni.core.util.ConstantVariable.KEY_WIDTH
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.userStore by preferencesDataStore(name = ConstantVariable.PREF_NAME)

class CanvasSizePreferences(context: Context) {
    private val userDataStore = context.userStore

    fun getCanvasSize(): Flow<CanvasSize> {
        return userDataStore.data.map { pref ->
            CanvasSize(
                width = pref[KEY_WIDTH] ?: 0,
                height = pref[KEY_HEIGHT] ?: 0,
            )
        }
    }

    suspend fun saveCanvasSize(canvasSize: CanvasSize) {
        userDataStore.edit { pref ->
            pref[KEY_WIDTH] = canvasSize.width
            pref[KEY_HEIGHT] = canvasSize.height
        }
    }

    suspend fun deleteCanvasSize() {
        userDataStore.edit { pref ->
            pref.clear()
        }
    }
}