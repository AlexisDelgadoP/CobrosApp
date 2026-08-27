package com.example.cobrosapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id_usuario: Int = 0,
    val nombre: String,
    val rol: String,
    val email: String,
    val password_hash: String
)
