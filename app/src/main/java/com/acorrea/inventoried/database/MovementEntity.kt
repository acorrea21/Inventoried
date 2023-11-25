package com.acorrea.inventoried.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovementEntity
    (
    @PrimaryKey(autoGenerate = true) val mid: Long = 0,
    @ColumnInfo(name = "product_name") val name: String?,
    @ColumnInfo(name = "type") val type: Int?,
    @ColumnInfo(name = "details") val details: String?,
    @ColumnInfo(name = "date") val date: String?,
)