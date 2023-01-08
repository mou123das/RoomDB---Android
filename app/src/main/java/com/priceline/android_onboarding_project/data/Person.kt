package com.priceline.android_onboarding_project.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "person_table")
@Parcelize
data class Person (

    var firstName: String,
    var lastName: String,
    val emailAddress: String,
    var phoneNumber: String,
    val password: String,
    @Embedded
    var address: Address,
    @PrimaryKey(autoGenerate = true)
    var id: Int=0
) : Parcelable



//{}//create body of this class

