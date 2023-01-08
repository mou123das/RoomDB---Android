package com.priceline.android_onboarding_project.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Person::class], version = 1, exportSchema = false)


abstract class PersonDatabase: RoomDatabase(){

    abstract fun personDao(): PersonDao

    companion object{

        private var INSTANCE: PersonDatabase?=null

        fun getInstance(context: Context): PersonDatabase{

            if(INSTANCE==null) {
                synchronized(PersonDatabase::class)
                {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }
        private fun buildRoomDB(context: Context)= Room.databaseBuilder(context.applicationContext, PersonDatabase::class.java, "person_database").allowMainThreadQueries().build()
    }
}