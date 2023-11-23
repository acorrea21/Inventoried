package com.acorrea.inventoried.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovementDAO {
    @Query("SELECT * FROM MovementEntity")
    fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM MovementEntity WHERE mid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<MovementEntity>

    /*@Query("SELECT * FROM ProductEntity WHERE name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User*/

    @Insert
    fun insertAll(vararg users: MovementEntity)

    @Delete
    fun delete(user: MovementEntity)
}