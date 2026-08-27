package com.example.cobrosapp.dao

import androidx.room.*
import com.example.cobrosapp.entities.Producto

@Dao
interface ProductoDao {
    @Insert fun insert(producto: Producto): Long
    @Update fun update(producto: Producto): Int
    @Delete fun delete(producto: Producto): Int

    @Query("SELECT * FROM Producto WHERE nombre LIKE '%' || :nombre || '%'")
    fun searchByNombre(nombre: String): List<Producto>
}
