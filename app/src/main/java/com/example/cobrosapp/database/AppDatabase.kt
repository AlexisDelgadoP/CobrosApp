package com.example.cobrosapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cobrosapp.entities.*
import com.example.cobrosapp.dao.*

@Database(
    entities = [Usuario::class, Cliente::class, Producto::class, ProductoCliente::class, Cobro::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun clienteDao(): ClienteDao
    abstract fun productoDao(): ProductoDao
    abstract fun productoClienteDao(): ProductoClienteDao
    abstract fun cobroDao(): CobroDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cobros_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }

}
