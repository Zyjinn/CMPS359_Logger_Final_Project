package com.example.cmps359_logger_final_project

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "times")
data class Times(@PrimaryKey(autoGenerate = true) @NonNull @ColumnInfo(name = "timeId")
var timeId: Int = 0,
    @ColumnInfo(name = "gameId") var gameId: Int,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "totalTime") var totalTime: Long)