package com.vargas.tarea2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.vargas.tarea2.BaseDeDatos.DatabaseHelper
import com.vargas.tarea2.databinding.ActivityMainResgitrarBinding
import com.vargas.tarea2.ui.RegistrarViewModel

class MainResgitrar : AppCompatActivity() {
    //Clase de configuracion (Colocar en cada clase) si quiero trabajarlo
    private lateinit var binding: ActivityMainResgitrarBinding;
    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainResgitrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseHelper = DatabaseHelper(this)
        configurarCheckbox()
        configuracionSppiners()
        botonRegistrar()

    }
    private fun botonRegistrar() {
        val submitButton = binding.buttonResgistrar
        //Configurar el botón resgitrar
        binding.buttonResgistrar.setOnClickListener {
            //VerificarCheckBox

            if (verificacionDeCampos()) {
                if (verificarChexbox(binding.checkBox)) {
                    AlertDialog.Builder(this)
                        .setTitle("Confirmacion")
                        .setMessage("¿Estas seguro de deseas guardar los datos?")
                        .setCancelable(false)
                        .setPositiveButton(android.R.string.ok) { dialog, which ->
                            guardarRegistro()
                            guardarEnBdRegistro()
                            Snackbar.make(
                                binding.root,
                                "Se han guardado los datos",
                                Snackbar.LENGTH_SHORT
                            )
                                .show()
                            limpiar()
                        }
                        .setNegativeButton(android.R.string.cancel) { dialog, which ->
                            Toast.makeText(
                                applicationContext,
                                "Se ha cancelado la accion",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .show()
                }
            }
        }
    }

    private fun guardarEnBdRegistro() {
        val nombre = binding.editNombre.text.toString()
        val apellido = binding.editApellido.text.toString()
        val edad = binding.spinnerEdad.selectedItem.toString().toInt()
        val email = binding.editTextTextEmailAddress3.text.toString()
        val password = binding.editTextTextPassword3.text.toString()
        val phone = binding.editTextPhone.text.toString()
        val generoFavorito = binding.SpinnerGeneroFavorito.selectedItem.toString()
        //Insertar los datos en la base de datos
        val result:Long= databaseHelper.insertarPeliculas(nombre,apellido,edad,email,password,phone,generoFavorito)
        if (result==-1L){
            Toast.makeText(this,"No se pudo resgistrar el usuario",Toast.LENGTH_SHORT).show()
            Log.e("Error","No se pudo resgistrar el usuario")
        }else{
            Log.i("Database","Se ha registrado el usuario con ID: $result")
            Toast.makeText(this,"Se ha registrado el usuario",Toast.LENGTH_SHORT).show()
        }
        limpiarTextBD()
    }

    private fun limpiarTextBD() {
        binding.editNombre.text.clear()
        binding.editApellido.text.clear()
        binding.editTextTextEmailAddress3.text.clear()
        binding.editTextTextPassword3.text.clear()
        binding.editTextPhone.text.clear()
        binding.spinnerEdad.setSelection(1)
        binding.SpinnerGeneroFavorito.setSelection(1)
        binding.checkBox.isChecked = false
        Toast.makeText(this,"Se ha limpiado la base de datos",Toast.LENGTH_SHORT).show()
    }

    private fun verificacionDeCampos(): Boolean {
        val nombre = binding.editNombre.text.toString().trim()
        val apellido = binding.editApellido.text.toString().trim()
        val email = binding.editTextTextEmailAddress3.text.toString().trim()
        val password = binding.editTextTextPassword3.text.toString().trim()
        val phone = binding.editTextPhone.text.toString().trim()
        return  when{
            email.isEmpty()->{
                binding.editTextTextEmailAddress3.error = "El email es obligatorio"
                false
            }
            password.isEmpty()->{
                binding.editTextTextPassword3.error = "El password es obligatorio"
                false
            }
            phone.isEmpty()->{
                binding.editTextPhone.error = "El telefono es obligatorio"
                false
            }
            nombre.isEmpty() -> {
                binding.editNombre.error = "El nombre es obligatorio"
                false
            }
            apellido.isEmpty()->{
                binding.editApellido.error = "El apellido es obligatorio"
                false
            }
            else -> true
        }
    }

    private fun limpiar() {
        //Vaciar los campos
        binding.editNombre.text.clear()
        binding.editApellido.text.clear()
        binding.editTextTextEmailAddress3.text.clear()
        binding.editTextTextPassword3.text.clear()
        binding.editTextPhone.text.clear()
    }

    private fun guardarRegistro() {
        val resgitrarViewModel = ViewModelProvider(this).get(RegistrarViewModel::class.java)
        //Aqui pasamos los parametros para llamar a la clase ViewModel
        //Para resgistrarlo en el JSON
        val nombre = binding.editNombre.text.toString()
        val apellido = binding.editApellido.text.toString()
        val email = binding.editTextTextEmailAddress3.text.toString()
        val password = binding.editTextTextPassword3.text.toString()
        val phone = binding.editTextPhone.text.toString()
        val edad = binding.spinnerEdad.selectedItem.toString().toInt()
        val generoFavorito = binding.SpinnerGeneroFavorito.selectedItem.toString()
        //Llamamos al viewModel para guardar los dato
        resgitrarViewModel.registrarUser(
            email,
            password,
            phone,
            edad,
            generoFavorito,
            nombre,
            apellido,
            this
        )
    }

    private fun configuracionSppiners() {
        //Configurar el Spinner
        val spinnerEdad = binding.spinnerEdad
        val spinnerGeneroFavorito = binding.SpinnerGeneroFavorito
        //Iniciamos los ArrayAdapter del recurso String-array
        val adapterEdad = ArrayAdapter.createFromResource(
            this,
            R.array.edad,
            android.R.layout.simple_spinner_item
        )
        val adapterGeneroFavorito = ArrayAdapter.createFromResource(
            this,
            R.array.GeneroFavorito,
            android.R.layout.simple_spinner_item
        )
        //Especificar el diseño del menú desplegable
        adapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterGeneroFavorito.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Aplicar el adaptador al spinner
        spinnerEdad.adapter = adapterEdad
        spinnerEdad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    spinnerEdad.setSelection(1)
                    //Seleccion automaticamente el segundo elemento
                    Toast.makeText(
                        this@MainResgitrar,
                        "Selecciona una edad valida",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        spinnerGeneroFavorito.adapter = adapterGeneroFavorito
        spinnerGeneroFavorito.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    spinnerGeneroFavorito.setSelection(1)
                    //Seleccion automaticamente el segundo elemento
                    Toast.makeText(
                        this@MainResgitrar,
                        "Selecciona una Genero valido",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun configurarCheckbox(){
        val checkBox = binding.checkBox
        //Configurar el botón Resgitrar(Terminos y Condiciones)
        val text = "Acepta los Terminos y Condiciones"
        val spannableString = SpannableString(text)
        val startIndex = text.indexOf("Terminos")
        val endIndex = text.indexOf("Condiciones") + "Condiciones".length
        //Subrayado del texto
        spannableString.setSpan(
            UnderlineSpan(),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        //Click para redirgir al activity de Terminos y Condiciones
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@MainResgitrar, MainTerminosYCondiciones::class.java)
                //Llamamos al activity
                startActivity(intent)
            }

            //Le damos el color al texto
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                //Subraya el texto
                ds.color = Color.BLUE
                //Le damos el color al texto en azul
            }
        }
        //Habilitamos los clickables en el texto
        spannableString.setSpan(
            clickableSpan,
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        //Añadimos el texto al checkbox
        checkBox.text = spannableString
        //Habilitar los clickables
        checkBox.movementMethod = LinkMovementMethod.getInstance()
    }
    private fun verificarChexbox(checkBox: CheckBox): Boolean {

        //Verificar el estado del checkbox
        if (checkBox.isChecked) {
            //Si esta marcado, mostrar un mensaje
            Toast.makeText(
                this, "Gracias por aceptar los terminos y condiciones.", Toast.LENGTH_SHORT
            ).show()
            //Eliminar despues de darle el chek
            checkBox.isChecked = false
            return true
        } else {
            //Si no esta marcado, mostrar un mensaje
            Toast.makeText(
                this, "Debes aceptar los terminos y condiciones.", Toast.LENGTH_SHORT
            ).show()
            return false
        }

    }
}