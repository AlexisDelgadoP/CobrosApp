package com.example.cobrosapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cliente(
    @PrimaryKey(autoGenerate = true) val id_cliente: Int = 0,
    val nombre: String,
    val ciudad: String,
    val descripcion: String?,
    val latitud: Double?,
    val longitud: Double?
)
