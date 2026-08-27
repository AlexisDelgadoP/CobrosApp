package com.example.cobrosapp.dao

import androidx.room.*
import com.example.cobrosapp.entities.Usuario

@Dao
interface UsuarioDao {
    @Insert fun insert(usuario: Usuario): Long
    @Update fun update(usuario: Usuario): Int
    @Delete fun delete(usuario: Usuario): Int

    @Query("SELECT * FROM Usuario WHERE email = :email")
    fun getByEmail(email: String): Usuario?
}
