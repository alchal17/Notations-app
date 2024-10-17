package com.example.notations.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notation(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val description: String,
    val createdAt: Long,
    val setTo: Long
)
