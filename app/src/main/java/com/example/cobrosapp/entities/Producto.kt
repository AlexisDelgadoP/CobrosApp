package com.example.cobrosapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Producto(
    @PrimaryKey(autoGenerate = true) val id_producto: Int = 0,
    val nombre: String,
    val precio_referencia: Double
)
