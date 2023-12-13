package com.beni.core.data.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beni.core.util.ConstantVariable.ENTITY_NAME

@Entity(tableName = ENTITY_NAME)
data class PointEntitiy(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "x")
    val x: Float,

    @ColumnInfo(name = "y")
    val y: Float,
)
