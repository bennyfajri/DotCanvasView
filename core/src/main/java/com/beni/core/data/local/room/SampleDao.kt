package com.beni.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.beni.core.util.ConstantVariable.ENTITY_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface SampleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoints(listEntity: List<PointEntitiy>)

    @Query("SELECT * FROM $ENTITY_NAME")
    fun getAllPoint(): Flow<List<PointEntitiy>>

    @Query("SELECT * FROM $ENTITY_NAME WHERE x <= :x AND y <= :y")
    fun getPointByPosition(x: Float, y: Float): Flow<List<PointEntitiy>>

    @Query("DELETE FROM $ENTITY_NAME")
    suspend fun removeAllPoint()
}