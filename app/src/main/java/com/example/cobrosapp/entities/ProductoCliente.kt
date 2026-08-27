package com.example.cobrosapp.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Cliente::class,
            parentColumns = ["id_cliente"],
            childColumns = ["id_cliente"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Producto::class,
            parentColumns = ["id_producto"],
            childColumns = ["id_producto"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id_usuario"],
            childColumns = ["id_usuario_vendedor"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    // AÑADIMOS ESTO PARA EVITAR EL WARNING Y MEJORAR RENDIMIENTO
    indices = [
        Index("id_cliente"),
        Index("id_producto"),
        Index("id_usuario_vendedor")
    ]
)
data class ProductoCliente(
    @PrimaryKey(autoGenerate = true) val id_producto_cliente: Int = 0,
    val id_cliente: Int,
    val id_producto: Int,
    val id_usuario_vendedor: Int?,
    val cantidad: Int,
    val precio_acordado_total: Double,
    val entrega_inicial: Double?,
    val monto_por_cuota: Double,
    val cuotas_totales: Int,
    val cuotas_pagadas: Int,
    val fecha_inicio_cobro: String,
    val frecuencia_cobro: String
)