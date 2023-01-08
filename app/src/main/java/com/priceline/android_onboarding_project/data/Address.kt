package com.priceline.android_onboarding_project.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(

    var street:String,
    var city:String,
    var pincode: Int
) :Parcelable
