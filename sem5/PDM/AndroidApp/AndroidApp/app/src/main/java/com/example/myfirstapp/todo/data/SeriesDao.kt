package com.example.myfirstapp.todo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SeriesDao {
    @Query("SELECT * FROM Series")
    fun getAll(): List<Series>

    @Query("SELECT * FROM Series WHERE _id == :id")
    fun getById(id: String): List<Series>

    @Insert
    fun insert(series: Series)

    @Update
    fun update(series: Series)

    @Query("DELETE FROM Series")
    fun clear()

    @Query("DELETE FROM Series WHERE _id == :id")
    fun deleteById(id:String)
}