package com.beni.core.data

import com.beni.core.data.local.LocalDataSource
import com.beni.core.data.local.preferences.CanvasSize
import com.beni.core.data.local.room.PointEntitiy
import com.beni.core.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SampleRepository @Inject constructor(
    private val localData: LocalDataSource
) {
    fun getCanvasSize() = localData.getCanvasSize()

    suspend fun saveCanvasSize(canvasSize: CanvasSize) = localData.saveCanvasSize(canvasSize)

    suspend fun deleteCanvasSize() = localData.deleteCanvasSize()

   fun getAllPont() = localData.getAllPoint()

   suspend fun insertPoints(list: List<PointEntitiy>) = localData.insertPoints(list)

    fun getPointByPosition(x: Float, y: Float): Flow<List<PointEntitiy>> = localData.getPointByPosition(x, y)

   suspend fun removeAllPoint() = localData.removeAllPoint()
}