package com.vargas.tarea2.BaseDeDatos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        //Definimos el nombre de la base de datos
        private const val DATABASE_NAME = "APPFILM.db"
        private const val DATABASE_VERSION = 1
        //Nombre de la columna de las tablas
        const val TABLE_REGISTRO = "RegistrosClientes"
        //Definimos las columnas de la tabla
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_APELLIDO = "apellido"
        const val COLUMN_EDAD = "edad"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_GENERO_DE_Pelicula = "generofavorito"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Sentencia SQL para crear la tabla "parque"
        //Este código generará la siguiente cadena final, sin sangrías al principio de cada línea:
        /* val createTable = """
             CREATE TABLE $TABLE_PARQUE (
                 $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                 $COLUMN_NOMBRE TEXT,
                 $COLUMN_DESCRIPCION TEXT,
                 $COLUMN_TELEFONO TEXT
             )
         """.trimIndent()*/
        val createTable = "CREATE TABLE RegistrosClientes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, " +
                "apellido TEXT, " +
                "edad INTEGER,"+
                "email TEXT," +
                "password TEXT,"+
                "phone TEXT,"+
                "generofavorito TEXT" +
                ")"

        /*val createTable = "CREATE TABLE $TABLE_PARQUE (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, " +
                "descripcion TEXT, " +
                "telefono TEXT" +
                ")"*/
        // Ejecuta la sentencia SQL para crear la tabla en la base de datos
        db?.execSQL(createTable)
    }
    //Actualizar la estructura de la base de datos sin afectar a los datos almacenados
    override fun onUpgrade(db:SQLiteDatabase, oldVersion: Int, newVersion: Int) {
       //Eliminar la tabla si existe
        db.execSQL("DROP TABLE IF EXISTS $TABLE_REGISTRO")
        //Volvemos a crear la tabla
        onCreate(db)
    }
    //Insertar un registro en la tabla
    fun insertarPeliculas(nombre:String,apellido:String,edad:Int,email:String,password:String,phone:String,generofavorito:String):Long{
        //Obiene la base de datos en modo escritura
        val db = writableDatabase
        //Crear un objeto ContentValues para almacenar los valores a insertar
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE,nombre)
            put(COLUMN_APELLIDO, apellido)
            put(COLUMN_EDAD, edad)
            put(COLUMN_EMAIL,email)
            put(COLUMN_PASSWORD,password)
            put(COLUMN_PHONE,phone)
            put(COLUMN_GENERO_DE_Pelicula,generofavorito)
        }
        return db.insert(TABLE_REGISTRO,null,values)
    }
    fun getAllPeliculas():Cursor{
        //Obtiene la base de datos en modo lectura
        val db:SQLiteDatabase = readableDatabase
        //Ejecuta la consulta SQL para obtener todos los registros de la tabla
        return db.query(TABLE_REGISTRO,null,null,null,null,null,null)
    }
}