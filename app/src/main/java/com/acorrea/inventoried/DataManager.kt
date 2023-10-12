package com.acorrea.inventoried

import com.acorrea.inventoried.entity.Product
import com.acorrea.inventoried.entity.ProductInventory
import com.acorrea.inventoried.entity.ProductShopping
import com.acorrea.inventoried.entity.StoreData
import com.acorrea.inventoried.entity.UserData

object DataManager
{
    init
    {
        println("Singleton DataManager invoked.")
    }

    private var users = arrayListOf<UserData>()
    var stores = arrayListOf<StoreData>()
    var products = arrayListOf<Product>()
    var productsInventory = arrayListOf<ProductInventory>()
    var productsShopping = arrayListOf<ProductShopping>()

    fun AddUser(userData: UserData)
    {
        users.add(userData)
    }

    fun GetUsers(): ArrayList<UserData>
    {
        return users;
    }
}