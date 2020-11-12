package com.example.cmps359_logger_final_project
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TimesDao {

    @Insert
    fun insertTime(time: Times)

    @Query("SELECT * FROM times WHERE gameId = :id ORDER BY totalTime")
    fun getTimes(id: Int): List<Times>

    @Query("UPDATE times SET totalTime = :time WHERE gameId = :id AND username = :name")
    fun updateTimes(time: Long, id: Int, name: String)
}