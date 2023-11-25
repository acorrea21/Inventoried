package com.acorrea.inventoried.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.acorrea.inventoried.database.ProductEntity

@Dao
interface ProductDAO {
    @Query("SELECT * FROM ProductEntity")
    fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE pid IN (:pid)")
    fun loadAllByIds(pid: IntArray): List<ProductEntity>

    /*@Query("SELECT * FROM ProductEntity WHERE name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User*/

    @Update
    fun update(product: ProductEntity)

    @Insert
    fun insertAll(vararg products: ProductEntity)

    @Insert
    fun insert(product: ProductEntity)

    @Delete
    fun delete(product: ProductEntity)
}