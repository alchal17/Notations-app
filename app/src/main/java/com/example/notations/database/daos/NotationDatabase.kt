package com.example.notations.database.daos

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notations.models.Notation

@Database(entities = [Notation::class], version = 1)
abstract class NotationDatabase : RoomDatabase() {
    abstract val dao: NotationDao

}