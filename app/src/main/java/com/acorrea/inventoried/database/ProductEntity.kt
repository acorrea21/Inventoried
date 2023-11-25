package com.acorrea.inventoried.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity
 (
 @PrimaryKey(autoGenerate = true) val pid: Long = 0,
 @ColumnInfo(name = "name") val name: String?,
 @ColumnInfo(name = "brand") val brand: String?,
 @ColumnInfo(name = "type") val type: Int?,
 @ColumnInfo(name = "amount") val amount: Int?,
 @ColumnInfo(name = "price") val price: Int?,
)

