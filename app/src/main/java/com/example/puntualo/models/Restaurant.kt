package com.example.puntualo.models

import android.os.Parcel
import android.os.Parcelable

data class Restaurant(
    val address: String?,
    val name: String?,
    val phone: String?,
    val photo: String?,
    val ruc: String?,
    val specialty: String?,
    val latitude: String?,
    val longitude: String?,
    val id: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(photo)
        parcel.writeString(ruc)
        parcel.writeString(specialty)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Restaurant> {
        override fun createFromParcel(parcel: Parcel): Restaurant {
            return Restaurant(parcel)
        }

        override fun newArray(size: Int): Array<Restaurant?> {
            return arrayOfNulls(size)
        }
    }
}
