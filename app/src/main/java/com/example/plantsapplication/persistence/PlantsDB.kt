package com.example.plantsapplication.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.plantsapplication.data.vos.PlantVO
import com.example.plantsapplication.persistence.daos.PlantDao
import com.example.plantsapplication.utilites.DB_NAME

@Database(entities = arrayOf(PlantVO::class), version = 5, exportSchema = false)
abstract class PlantsDB: RoomDatabase() {

    abstract fun plantDao(): PlantDao

    companion object{
        private var instance : PlantsDB?= null

        fun getInstance(context: Context): PlantsDB{
            if (instance == null){
                instance = Room.databaseBuilder(context, PlantsDB::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }

    /*fun areDatasExitInDB(): Boolean{
        return plantDao().getPlants().isNotEmpty()
    }*/
}