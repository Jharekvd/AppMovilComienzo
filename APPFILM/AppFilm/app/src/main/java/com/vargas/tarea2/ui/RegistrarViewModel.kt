package com.vargas.tarea2.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileWriter

class RegistrarViewModel : ViewModel() {
    private val fileName ="filmRegistro.json"
    fun registrarUser(Email:String,
                         Password:String,
                         Phone:String,
                         Edad:Int,
                         Annio:Int,
                         nombre:String,
                         apellidos:String,
                         //context: Context para poder acceder a los archivos
                         context: Context) {
        val nuevoRegis = JSONObject().apply {
            put("Email", Email)
            put("Password", Password)
            put("Phone", Phone)
            put("Edad", Edad)
            put("Annio", Annio)
            put("nombre", nombre)
            put("apellidos", apellidos)
        }
        saveJsonToFile(nuevoRegis, context )
    }
    //Metodo para guardar el archivo en JSON
    private fun saveJsonToFile(nuevoRegis:JSONObject,context: Context) {
        val file = File(context.filesDir, fileName)
        try {
            if (file.exists()){
                val existingJson = file.readText()
                val jsonArray = JSONArray(existingJson)
                jsonArray.put(nuevoRegis)
                FileWriter(file).use { writer ->writer.write(jsonArray.toString(4)) }
            }else{
                val jsonArray = JSONArray()
                jsonArray.put(nuevoRegis)
                FileWriter(file).use { writer ->writer.write(jsonArray.toString(4)) }
            }
            Log.d("RegistrarViewModel", "Datos guardados correctamente")
        }catch (e:Exception){
            Log.e("RegistrarViewModel", "Error al guardar los datos",e)
        }
    }
}