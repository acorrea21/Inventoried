package com.acorrea.inventoried.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovementDAO
{
    @Query("SELECT * FROM MovementEntity")
    fun getAll(): List<MovementEntity>

    @Query("SELECT * FROM MovementEntity WHERE mid IN (:movementID)")
    fun loadAllByIds(movementID: IntArray): List<MovementEntity>

    @Insert
    fun insertAll(vararg users: MovementEntity)

    @Insert
    fun insert(movement: MovementEntity)

    @Delete
    fun delete(user: MovementEntity)
}