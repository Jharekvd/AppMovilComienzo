package com.vargas.tarea2.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vargas.tarea2.DatasClass.Usuario
import org.json.JSONArray
import java.io.File

class MostrarUsuariosViewModel : ViewModel() {
    private val _usuarios = MutableLiveData<List<Usuario>>()

    /*con get() estamos definiendo una propiedad de solo lectura
    no podra ser modificada desde fuera de la clase*/
    val usuarios: LiveData<List<Usuario>> get() = _usuarios
    var JSONArray: JSONArray? = null
    private val fileName = "filmRegistro.json"
    //contex los archivos del device explorer
    fun mostrarUsuario(context: Context) {
        val file = File(context.filesDir, fileName)
        if (file.exists()) {
            //leemos el archivo json
            try {
                val json = file.readText()
                //Convertimos el json a un JSONArray
                JSONArray = JSONArray(json)
                val listusuarios = mutableListOf<Usuario>()
                //Recorremos el JSONArray e Iteramos sobre cada objeto
                for (i in 0 until JSONArray!!.length()) {
                    //Obtenemos el objeto JSON actual
                    val jsonObject = JSONArray?.getJSONObject(i)?:continue
                    val nombre = jsonObject.getString("nombre")
                    val apellidos = jsonObject.getString("apellidos")
                     val phone = jsonObject.getString("Phone")
                    val email = jsonObject.getString("Email")
                    val password = jsonObject.getString("Password")
                    val edad = jsonObject.getInt("Edad")
                    val annio = jsonObject.getInt("Annio")
                    //Creamos un objeto Usuario con los datos obtenidos
                    val usuario = Usuario(nombre,apellidos,phone, email, password, edad, annio)
                    listusuarios.add(usuario)

                }
                //Actualizamos el valor de _usuarios con la lista de usuarios
                _usuarios.value = listusuarios
            } catch (e: Exception) {
                e.printStackTrace()
            }


        } else {
            //Si el archivo no existe, actualizamos el valor de _usuarios con una lista vacia
            _usuarios.value = emptyList()
        }

    }

}