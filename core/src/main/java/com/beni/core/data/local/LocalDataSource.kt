package com.beni.core.data.local

import com.beni.core.data.local.preferences.CanvasSize
import com.beni.core.data.local.preferences.CanvasSizePreferences
import com.beni.core.data.local.room.SampleDao
import com.beni.core.data.local.room.PointEntitiy
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val pref: CanvasSizePreferences,
    private val room: SampleDao
) {
    fun getCanvasSize() = pref.getCanvasSize()

    suspend fun saveCanvasSize(canvasSize: CanvasSize) = pref.saveCanvasSize(canvasSize)

    suspend fun deleteCanvasSize() = pref.deleteCanvasSize()

    suspend fun insertPoints(listEntity: List<PointEntitiy>) = room.insertPoints(listEntity)

    fun getAllPoint(): Flow<List<PointEntitiy>> = room.getAllPoint()

    fun getPointByPosition(x: Float, y: Float): Flow<List<PointEntitiy>> = room.getPointByPosition(x, y)

    suspend fun removeAllPoint() = room.removeAllPoint()
}