package com.example.myapplication

import android.os.Bundle
import android.os.Environment
import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.time.LocalDateTime
import com.example.myapplication.WriteReadFile

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
        val context = LocalContext.current

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    var outS = WriteReadFile.guardarTextoEnArchivo(context, datetime, nombreArchivo)
                    Log.i("DAM2", outS)
                }
            ) {
                Text("Guardar archivo")
            }
            Button(
                onClick = {
                    var inS = WriteReadFile.leerTextoDeArchivo(context, nombreArchivo)
                    Log.i("DAM2", inS)
                }
            ) {
                Text("Leer archivo")
            }
        }
    }
}