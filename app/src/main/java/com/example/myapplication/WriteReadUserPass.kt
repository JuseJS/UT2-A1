package com.example.myapplication

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class WriteReadUserPass {
    companion object {
        fun saveUser(context: Context, user: String, pass: String, fileName: String):String {
            val storageState = Environment.getExternalStorageState()

            if (storageState == Environment.MEDIA_MOUNTED) {
                val directory = context.filesDir
                val file = File(directory, fileName)

                try {
                    val outputStream = FileOutputStream(file, true)
                    val writer = OutputStreamWriter(outputStream)
                    writer.append("\"$user\":\"$pass\"\n")
                    writer.close()

                    return "Usuario guardado en $directory $fileName"
                } catch (e: Exception) {
                    e.printStackTrace()
                    return "Error al guardar el usuario"
                }
            } else {
                return "No se pudo acceder al almacenamiento externo"
            }
        }

        fun readUsers(context: Context, fileName: String):String {
            val storageState = Environment.getExternalStorageState()
            if (storageState == Environment.MEDIA_MOUNTED) {
                val directory = context.filesDir
                val file = File(directory, fileName)

                try {
                    val inputStream = FileInputStream(file)
                    val reader = InputStreamReader(inputStream)
                    val text = reader.readText()
                    reader.close()

                    return "Usuarios guardados en $fileName: \n $text"
                } catch (e: Exception) {
                    e.printStackTrace()
                    return "Error al leer los usuarios"
                }
            } else {
                return "No se pudo acceder al almacenamiento externo"
            }
        }
    }
}