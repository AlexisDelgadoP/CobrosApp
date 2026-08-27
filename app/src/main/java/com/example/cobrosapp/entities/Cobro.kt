package com.example.cobrosapp.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ProductoCliente::class,
            parentColumns = ["id_producto_cliente"],
            childColumns = ["id_producto_cliente"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id_usuario"],
            childColumns = ["id_usuario_cobrador"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    // AÑADIMOS ESTO TAMBIÉN AQUÍ
    indices = [
        Index("id_producto_cliente"),
        Index("id_usuario_cobrador")
    ]
)
data class Cobro(
    @PrimaryKey(autoGenerate = true) val id_cobro: Int = 0,
    val id_producto_cliente: Int,
    val id_usuario_cobrador: Int?,
    val fecha_cobro: String,
    val monto: Double,
    val estado: String // pendiente, pagado, retrasado
)