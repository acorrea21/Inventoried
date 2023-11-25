package com.acorrea.inventoried.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class,MovementEntity::class], version = 1)
abstract class MyDatabase : RoomDatabase()
{
    abstract fun productDao(): ProductDAO

    abstract fun movementDao(): MovementDAO

}