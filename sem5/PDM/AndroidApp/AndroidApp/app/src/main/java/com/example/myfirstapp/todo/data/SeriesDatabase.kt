package com.example.myfirstapp.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities=[Series::class], version=1, exportSchema = false)
abstract class SeriesDatabase : RoomDatabase() {
    abstract fun seriesDao(): SeriesDao
}