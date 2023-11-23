package com.acorrea.inventoried.oldEntities

import com.acorrea.inventoried.oldEntities.Product
import com.acorrea.inventoried.oldEntities.ProductInventory
import com.acorrea.inventoried.oldEntities.ProductShopping
import com.acorrea.inventoried.oldEntities.StoreData
import com.acorrea.inventoried.oldEntities.UserData

object DataManager
{
    init
    {
        println("Singleton DataManager invoked.")
    }

    var users = arrayListOf<UserData>()
    var stores = arrayListOf<StoreData>()
    var products = arrayListOf<Product>()
    var productsInventory = arrayListOf<ProductInventory>()
    var productsShopping = arrayListOf<ProductShopping>()
}