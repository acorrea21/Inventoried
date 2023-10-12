package com.acorrea.inventoried.entity

import android.os.Parcel
import android.os.Parcelable

data class StoreData(var name: String?, var description: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StoreData> {
        override fun createFromParcel(parcel: Parcel): StoreData {
            return StoreData(parcel)
        }

        override fun newArray(size: Int): Array<StoreData?> {
            return arrayOfNulls(size)
        }
    }
}
