package com.example.myfirstapp.todo.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Series(
    @PrimaryKey val _id: String = "${System.currentTimeMillis()*10000}",
    val title: String = "",
    val episodes: Int = 0,
    val date: String = "",
    val available: Boolean = true,
    val imageUrl: String="",
    var requiresCreate: Boolean=false,
    var requiresUpdate: Boolean=false
){
    override fun toString(): String =
        "$title \nepisodes:$episodes | date:$date | available:$available"
}