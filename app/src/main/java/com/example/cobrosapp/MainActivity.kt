package com.example.cobrosapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.cobrosapp.ui.theme.CobrosAppTheme
import com.example.cobrosapp.database.AppDatabase
import com.example.cobrosapp.entities.Cliente
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CobrosAppTheme {
                val context = LocalContext.current
                val scope = rememberCoroutineScope()

                // Inicializamos la base de datos a nivel de la Activity
                val db = AppDatabase.getDatabase(context)
                val clienteDao = db.clienteDao()

                // Llamamos a la vista y le pasamos SOLO lo que debe hacer al hacer clic
                PantallaPruebaRoom(
                    onGuardarCliente = {
                        // 1. Este Toast debe salir INMEDIATAMENTE al tocar el botón
                        Toast.makeText(context, "Procesando...", Toast.LENGTH_SHORT).show()

                        scope.launch {
                            try {
                                val nuevoCliente = Cliente(
                                    nombre = "Alexis Piris",
                                    ciudad = "Asunción",
                                    descripcion = "Primer cliente de prueba",
                                    latitud = -25.2637,
                                    longitud = -57.5759
                                )

                                // 2. Intentamos guardar en la base de datos
                                withContext(Dispatchers.IO) {
                                    clienteDao.insert(nuevoCliente)
                                }

                                // 3. Si llega hasta aquí, Room funcionó perfecto
                                Toast.makeText(context, "¡Cliente guardado con éxito!", Toast.LENGTH_LONG).show()

                            } catch (e: Exception) {
                                // 4. Si Room falla, capturamos el error y lo mostramos en pantalla
                                e.printStackTrace()
                                Toast.makeText(context, "Error fatal: ${e.message}", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                )
            }
        }
    }
}

// Esta función ahora es "tonta", solo dibuja la pantalla y avisa cuando hay un clic.
// Esto permite que el Preview funcione sin crashear.
@Composable
fun PantallaPruebaRoom(onGuardarCliente: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onGuardarCliente) {
            Text("Guardar Cliente de Prueba")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPantallaPruebaRoom() {
    CobrosAppTheme {
        // Para el preview, le pasamos una acción vacía {} para que no haga nada
        PantallaPruebaRoom(onGuardarCliente = {})
    }
}