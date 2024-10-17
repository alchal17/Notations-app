package com.example.notations.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notations.models.Notation
import kotlinx.coroutines.flow.Flow

@Dao
interface NotationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNotation(notation: Notation)

    @Delete
    suspend fun deleteNotation(notation: Notation)

    @Update
    suspend fun updateNotation(notation: Notation)

    @Query("SELECT * FROM notation")
    fun getAllNotations(): Flow<List<Notation>>

    @Query("SELECT * FROM Notation WHERE id = :id LIMIT 1")
    suspend fun findNotationById(id: Long): Notation?
}