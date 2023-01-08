package com.priceline.android_onboarding_project.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

//Defining all the methods for accessing the interface

@Dao
interface PersonDao {

    //by default returns long type
    //if some problem occurred then [-1]
    @Insert(onConflict =  OnConflictStrategy.IGNORE)
    fun addPerson(person: Person):Long

    @Query("SELECT * FROM person_table WHERE id=:id")
    fun fetchRecordById(id:Int): Person?

    @Query("SELECT * FROM person_table WHERE emailAddress=:email  AND password=:password ")
    fun fetchRecordWithValidation(email : String, password: String): Person?

    @Query("SELECT id FROM person_table WHERE emailAddress=:email")
    fun emailValidation(email : String): Int

    @Query("SELECT * FROM person_table ORDER BY id DESC")
    fun fetchAllPersons():List<Person>

//    @Query("DELETE FROM person_table WHERE id=:id")
//    fun deleteById(id : Int):Int

//    @Query("UPDATE person_table SET firstName=:firstName, lastName=:lastName, phoneNumber=:phoneNumber, city=:city, street=:street, pincode=:pincode WHERE emailAddress=:email")
//    fun updateDetailsByEmail(email:String, firstName: String, lastName: String, phoneNumber: String, city:String, street: String, pincode:Int):Int

    @Update
    fun updateDetails(person: Person):Int

    @Delete
    fun deletePerson(person: Person):Int




//    //fun fetchPersons():LiveData<List<Person>>

//    @Query("DELETE FROM person_table WHERE id=:id" )
//    fun deletePerson(id:Int)
//
//    @Query("DELETE FROM person_table")
//    fun deleteTable()
}