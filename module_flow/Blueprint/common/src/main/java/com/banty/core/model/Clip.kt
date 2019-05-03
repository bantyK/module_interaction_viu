package com.banty.core.model

import android.os.Parcel
import android.os.Parcelable

data class Clip(
        val id: String?,
        val title: String?,
        val isPaid: Boolean,
        val thumbUrl: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeByte(if (isPaid) 1 else 0)
        parcel.writeString(thumbUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Clip> {
        override fun createFromParcel(parcel: Parcel): Clip {
            return Clip(parcel)
        }

        override fun newArray(size: Int): Array<Clip?> {
            return arrayOfNulls(size)
        }
    }
}
