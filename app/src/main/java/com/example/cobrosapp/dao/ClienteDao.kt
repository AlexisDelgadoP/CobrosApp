package com.example.cobrosapp.dao

import androidx.room.*
import com.example.cobrosapp.entities.Cliente

@Dao
interface ClienteDao {

    // Devuelve un Long con el ID de la fila recién insertada
    @Insert
    suspend fun insert(cliente: Cliente): Long

    // Devuelve un Int con el número de filas actualizadas
    @Update
    suspend fun update(cliente: Cliente): Int

    // Devuelve un Int con el número de filas eliminadas
    @Delete
    suspend fun delete(cliente: Cliente): Int

    // Las consultas SELECT ya estaban perfectas porque devuelven List<Cliente>
    @Query("SELECT * FROM Cliente WHERE ciudad = :ciudad")
    suspend fun getByCiudad(ciudad: String): List<Cliente>
}