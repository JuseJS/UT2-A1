package com.example.myapplication

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen("ejemplo.txt")
                }
            }
        }
    }

    @Composable
    fun MainScreen(nombreArchivo: String) {
        val datetime = LocalDateTime.now().toString()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { WriteReadFile.guardarTextoEnArchivo(datetime, nombreArchivo) }
            ) {
                Text("Guardar archivo")
            }
            Button(
                onClick = { leerTextoDeArchivo(nombreArchivo) }
            ) {
                Text("Leer archivo")
            }
        }
    }

    private fun leerTextoDeArchivo(nombreArchivo: String) {
        val estadoAlmacenamiento = Environment.getExternalStorageState()
        if (estadoAlmacenamiento == Environment.MEDIA_MOUNTED) {
            val directorio = getFilesDir()
            val archivo = File(directorio, nombreArchivo)
            try {
                val flujoEntrada = FileInputStream(archivo)
                val reader = InputStreamReader(flujoEntrada)
                val texto = reader.readText()
                reader.close()
                Log.i("DAM2", "Contenido de $nombreArchivo: $texto")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("DAM2", "Error al leer el archivo")
            }
        } else {
            Log.e("DAM2", "No se pudo acceder al almacenamiento externo")
        }
    }
}