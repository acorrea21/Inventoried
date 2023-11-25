package com.acorrea.inventoried.oldEntities

import android.content.Context
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.acorrea.inventoried.database.MyDatabase
import com.acorrea.inventoried.database.ProductEntity
import com.acorrea.inventoried.R
object Utilities
{
    private var database: MyDatabase? = null

    fun getDatabase(context: Context) : MyDatabase
    {
        if (database == null)
        {
            database = Room.databaseBuilder(
                context.applicationContext,
                MyDatabase::class.java, "pepebase"
            ).allowMainThreadQueries().build()
        }


        return database!!
    }

    var products = listOf<ProductEntity>()

    fun getProductType(context: Context): List<String> {
        return listOf(
            context.getString(R.string.product_type_vegetables),
            context.getString(R.string.product_type_beverages),
            context.getString(R.string.product_type_fruits),
            context.getString(R.string.product_type_hygiene),
            context.getString(R.string.product_type_office),
            context.getString(R.string.product_type_others)
        )
    }

    fun getMovementType(context: Context): List<String>
    {
        return listOf(
            context.getString(R.string.movement_type_add),
            context.getString(R.string.movement_type_remove),
            context.getString(R.string.movement_type_change_name),
            context.getString(R.string.movement_type_change_amount),
            context.getString(R.string.movement_type_change_price),
            context.getString(R.string.movement_type_change_brand),
            context.getString(R.string.movement_type_change_type)
        )
    }

    fun getMovementIndex(context: Context, type: String) : Int
    {
        var index = 0
        val types = getMovementType(context)
        for (t in types)
        {
            if(type == t)
            {
                return index
            }
            index++
        }

        return -1
    }

    fun getPlayIntro(context: Context): Boolean
    {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        return pref.getBoolean("playintro", true)
    }

    var users = arrayListOf<UserData>()
    var stores = arrayListOf<StoreData>()
    var productsInventory = arrayListOf<ProductInventory>()
    var productsShopping = arrayListOf<ProductShopping>()
}