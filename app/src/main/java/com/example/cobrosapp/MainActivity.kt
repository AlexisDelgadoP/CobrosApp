package com.example.cobrosapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import com.example.cobrosapp.database.AppDatabase
import com.example.cobrosapp.entities.Usuario
import com.example.cobrosapp.ui.HomeScreen
import com.example.cobrosapp.ui.LoginScreen
import com.example.cobrosapp.viewmodel.LoginViewModel
import com.example.cobrosapp.viewmodel.LoginViewModelFactory


import java.security.MessageDigest

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(applicationContext)
        val usuarioDao = db.usuarioDao()

        // Insertar usuario demo si la tabla está vacía
        lifecycleScope.launch(Dispatchers.IO) {
            if (usuarioDao.getByEmail("demo@cobros.com") == null) {
                val demoUser = Usuario(
                    nombre = "Demo",
                    rol = "admin",
                    email = "demo@cobros.com",
                    password_hash = hashPassword("1234")
                )
                usuarioDao.insert(demoUser)
            }
        }

        val loginViewModel = ViewModelProvider(
            this,
            LoginViewModelFactory(usuarioDao)
        )[LoginViewModel::class.java]

        setContent {
            var isLoggedIn by remember { mutableStateOf(false) }

            if (isLoggedIn) {
                HomeScreen()
            } else {
                LoginScreen(
                    viewModel = loginViewModel,
                    onLoginSuccess = { isLoggedIn = true }
                )
            }
        }
    }

    fun hashPassword(password: String): String {
        return MessageDigest.getInstance("SHA-256")
            .digest(password.toByteArray())
            .joinToString("") { "%02x".format(it) }
    }
}
