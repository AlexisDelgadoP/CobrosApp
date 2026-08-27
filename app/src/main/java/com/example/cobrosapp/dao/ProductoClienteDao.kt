package com.example.cobrosapp.dao

import androidx.room.*
import com.example.cobrosapp.entities.ProductoCliente

@Dao
interface ProductoClienteDao {
    @Insert fun insert(pc: ProductoCliente): Long
    @Update fun update(pc: ProductoCliente): Int
    @Delete fun delete(pc: ProductoCliente): Int

    @Query("SELECT * FROM ProductoCliente WHERE id_cliente = :idCliente")
    fun getByCliente(idCliente: Int): List<ProductoCliente>
}
