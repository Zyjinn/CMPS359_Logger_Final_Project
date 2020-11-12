package com.example.cmps359_logger_final_project

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [(Times::class)], version = 1)
abstract class TimesRoomDatabase: RoomDatabase() {
    abstract fun timesDao(): TimesDao
    companion object {
        private var INSTANCE: TimesRoomDatabase? = null
        internal fun getDatabase(context: Context): TimesRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(TimesRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            Room.databaseBuilder<TimesRoomDatabase>(
                                context.applicationContext,
                                TimesRoomDatabase::class.java,
                                "times_database"
                            ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}