package com.acorrea.inventoried.oldEntities

import android.os.Parcel
import android.os.Parcelable

data class ProductInventory (var product: Product?, var amount: Int?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Product::class.java.classLoader),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(product, flags)
        parcel.writeValue(amount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductInventory> {
        override fun createFromParcel(parcel: Parcel): ProductInventory {
            return ProductInventory(parcel)
        }

        override fun newArray(size: Int): Array<ProductInventory?> {
            return arrayOfNulls(size)
        }
    }
}