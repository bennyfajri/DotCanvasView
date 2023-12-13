package com.beni.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        PointEntitiy::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class PointDatabase : RoomDatabase() {
    abstract fun sampleDao(): SampleDao
}