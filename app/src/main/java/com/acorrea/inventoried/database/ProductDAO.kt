package com.acorrea.inventoried.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.acorrea.inventoried.database.ProductEntity

@Dao
interface ProductDAO {
    @Query("SELECT * FROM ProductEntity")
    fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE pid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<ProductEntity>

    /*@Query("SELECT * FROM ProductEntity WHERE name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User*/

    @Insert
    fun insertAll(vararg users: ProductEntity)

    @Delete
    fun delete(user: ProductEntity)
}