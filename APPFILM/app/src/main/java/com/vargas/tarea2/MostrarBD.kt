package com.vargas.tarea2

import android.database.Cursor
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vargas.tarea2.BaseDeDatos.DatabaseHelper
import com.vargas.tarea2.databinding.ActivityMostrarBdBinding

class MostrarBD : AppCompatActivity() {
    private lateinit var binding: ActivityMostrarBdBinding
    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarBdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseHelper = DatabaseHelper(this)
        mostrarBd()
    }
    fun mostrarBd(){
        binding.ButtonMostrarBd.setOnClickListener(){
            //Obtener los datos de la base de datos
            val cursor = databaseHelper.getAllPeliculas()
            //Recorrer el cursor para obtener los datos
            val registros = StringBuilder()
            //Recorrer el cursor para obtener los datos
            while (cursor.moveToNext()){
                val nombre:String = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOMBRE))
                val apellido:String = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_APELLIDO))
                val edad:Int = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EDAD))
                val email:String = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL))
                val password:String = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PASSWORD))
                val phone:String = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PHONE))
                val generoFavorito:String = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_GENERO_DE_Pelicula))
                registros.append("Usuario: $nombre $apellido $edad \n $email $password $phone \n $generoFavorito \n")
            }
            mostrarLista(registros,cursor)
        }
    }
    fun mostrarLista(registros: StringBuilder, cursor: Cursor) {
        binding.MostrarBD.text = registros.toString()
        cursor.close()
    }
}