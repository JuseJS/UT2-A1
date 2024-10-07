package com.example.myapplication

import android.os.Environment
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class WriteReadFile {
    fun guardarTextoEnArchivo(texto: String, nombreArchivo: String):String {
        val estadoAlmacenamiento = Environment.getExternalStorageState()

        if (estadoAlmacenamiento == Environment.MEDIA_MOUNTED) {
            val directorio = getFilesDir()
            val archivo = File(directorio, nombreArchivo)

            try {
                val flujoSalida = FileOutputStream(archivo, true)
                val writer = OutputStreamWriter(flujoSalida)
                writer.append(texto)
                writer.close()

                return "Texto añadido en $directorio $nombreArchivo"
            } catch (e: Exception) {
                e.printStackTrace()
                return "Error al guardar el archivo"
            }
        } else {
            return "No se pudo acceder al almacenamiento externo"
        }
    }
}