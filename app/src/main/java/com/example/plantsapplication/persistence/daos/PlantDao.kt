package com.example.plantsapplication.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plantsapplication.data.vos.PlantVO

@Dao
abstract class PlantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPlants(movies: List<PlantVO>): LongArray

    @Query("select * from plant_db")
    abstract fun getPlants(): List<PlantVO>

    @Query("SELECT * FROM plant_db WHERE plant_id= :id")
    abstract fun getPlanstsById(id: String): PlantVO
}