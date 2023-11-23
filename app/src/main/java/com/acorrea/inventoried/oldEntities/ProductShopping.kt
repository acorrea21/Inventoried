package com.acorrea.inventoried.oldEntities

import android.os.Parcel
import android.os.Parcelable

data class ProductShopping(var product: Product?, var price: Int?, var amount: Int?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Product::class.java.classLoader),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(product, flags)
        parcel.writeValue(price)
        parcel.writeValue(amount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductShopping> {
        override fun createFromParcel(parcel: Parcel): ProductShopping {
            return ProductShopping(parcel)
        }

        override fun newArray(size: Int): Array<ProductShopping?> {
            return arrayOfNulls(size)
        }
    }
}
