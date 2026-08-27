package com.example.cobrosapp.dao

import androidx.room.*
import com.example.cobrosapp.entities.Cobro

@Dao
interface CobroDao {
    @Insert fun insert(cobro: Cobro): Long
    @Update fun update(cobro: Cobro): Int
    @Delete fun delete(cobro: Cobro): Int

    @Query("SELECT * FROM Cobro WHERE estado = :estado")
    fun getByEstado(estado: String): List<Cobro>
}
