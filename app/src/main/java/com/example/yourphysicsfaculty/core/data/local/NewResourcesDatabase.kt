package com.example.yourphysicsfaculty.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * The Room Database that contains the bookmarked news resources.
 *
 * Note that exportSchema should be true in production databases.
 */
@Database(entities = [NewsResourceEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewsResourceDatabase : RoomDatabase() {

    abstract fun NewsResourcesDao(): NewsResourcesDao

}
