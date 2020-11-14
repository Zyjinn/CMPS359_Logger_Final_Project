package com.example.cmps359_logger_final_project
// Nicholas Bourgeois, Jordan Williams
// C00222259, C00082940
// CMPS 359 - Android Programming
// Final Programming Project - A Speedrunning App, Loggers
// Certification of Authenticity:
// I certify that the code in this project are entirely of my own work
// the work of the members of my team or were caused to be generated by me or us

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