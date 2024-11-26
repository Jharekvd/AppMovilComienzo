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
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.vargas.tarea2.databinding.ActivityMainResgitrarBinding
import com.vargas.tarea2.ui.RegistrarViewModel

class MainResgitrar : AppCompatActivity() {
    //Clase de configuracion (Colocar en cada clase) si quiero trabajarlo
    private lateinit var binding: ActivityMainResgitrarBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainResgitrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val checkBox = binding.checkBox
        //Configurar el botón Resgitrar(Terminos y Condiciones)
        val text ="Acepta los Terminos y Condiciones"
        val spannableString = SpannableString(text)
        val startIndex = text.indexOf("Terminos")
        val endIndex = text.indexOf("Condiciones") + "Condiciones".length
        //Subrayado del texto
        spannableString.setSpan(UnderlineSpan(),startIndex,endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //Click para redirgir al activity de Terminos y Condiciones
        val clickableSpan  = object : ClickableSpan(){
            override fun onClick(widget: View) {
               val intent = Intent(this@MainResgitrar,MainTerminosYCondiciones::class.java)
                //Llamamos al activity
                startActivity(intent)
            }
            //Le damos el color al texto
            override fun updateDrawState(ds:TextPaint){
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                //Subraya el texto
                ds.color = Color.BLUE
                //Le damos el color al texto en azul
            }
        }
        //Habilitamos los clickables en el texto
        spannableString.setSpan(clickableSpan,startIndex,endIndex,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //Añadimos el texto al checkbox
        checkBox.text=spannableString
        //Habilitar los clickables
        checkBox.movementMethod= LinkMovementMethod.getInstance()

        //Configurar el Spinner
       val spinnerEdad = binding.spinnerEdad
        val spinnerAnio = binding.Spinneranio
        //Iniciamos los ArrayAdapter del recurso String-array
       val adapterEdad = ArrayAdapter.createFromResource(this,R.array.edad,android.R.layout.simple_spinner_item)
        val adapterAnio = ArrayAdapter.createFromResource(this,R.array.anio,android.R.layout.simple_spinner_item)
        //Especificar el diseño del menú desplegable
        adapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterAnio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //Aplicar el adaptador al spinner
        spinnerEdad.adapter = adapterEdad
        spinnerEdad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position==0){
                    spinnerEdad.setSelection(1)
                    //Seleccion automaticamente el segundo elemento
                    Toast.makeText(this@MainResgitrar, "Selecciona una edad valida", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        spinnerAnio.adapter = adapterAnio
        spinnerAnio.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position==0){
                    spinnerAnio.setSelection(1)
                    //Seleccion automaticamente el segundo elemento
                    Toast.makeText(this@MainResgitrar, "Selecciona una año valida", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        //Configurar el botón Resgitrar
        val submitButton = binding.buttonResgistrar
        val resgitrarViewModel = ViewModelProvider(this).get(RegistrarViewModel::class.java)
        //Configurar el botón resgitrar
        submitButton.setOnClickListener{
            AlertDialog.Builder(this)
                .setTitle("Confirmacion")
                .setMessage("¿Estas seguro de deseas guardar los datos?")
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok){ dialog , which ->
                    //Aqui pasamos los parametros para llamar a la clase ViewModel
                    //Para resgistrarlo en el JSON
                    val nombre = binding.editNombre.text.toString()
                    val apellido = binding.editApellido.text.toString()
                    val email = binding.editTextTextEmailAddress3.text.toString()
                    val password = binding.editTextTextPassword3.text.toString()
                    val phone = binding.editTextPhone.text.toString()
                    val edad = spinnerEdad.selectedItem.toString().toInt()
                    val anio = spinnerAnio.selectedItem.toString().toInt()
                    //Llamamos al viewModel para guardar los dato
                    resgitrarViewModel.registrarUser(
                        email,
                        password,
                        phone,
                        edad,
                        anio,
                        nombre,
                        apellido,
                        this)

                    Snackbar.make(binding.root,"Se han guardado los datos",Snackbar.LENGTH_SHORT).show()
                }
                .setNegativeButton(android.R.string.cancel){dialog,which ->
                    Toast.makeText(applicationContext,"Se ha cancelado la accion",Toast.LENGTH_SHORT).show()
                }
                .show()
            //Verificar el estado del checkbox
            if (checkBox.isChecked){
                //Si esta marcado, mostrar un mensaje
                Toast.makeText(this,"Gracias por aceptar los terminos y condiciones."
                    ,Toast.LENGTH_SHORT).show()
            }else{
                //Si no esta marcado, mostrar un mensaje
                Toast.makeText(this,"Debes aceptar los terminos y condiciones."
                    ,Toast.LENGTH_SHORT).show()
            }
            //Obtener el valor seleccionado del spinner
           /*val selectedEdad = spinnerEdad.selectedItem.toString()
            val selectedAnio = spinnerAnio.selectedItem.toString()
            //Mostrar el valor seleccionado con un Toast
            Toast.makeText(this, "Edad Seleccionada:$selectedEdad", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "Año Seleccionado:$selectedAnio", Toast.LENGTH_SHORT).show()
            //Mostrar los datos ingresados en los EditText*/
           /* val nombre = binding.editNombre.text.toString()
            val apellido = binding.editApellido.text.toString()
            val email = binding.editTextTextEmailAddress3.text.toString()
            val password = binding.editTextTextPassword3.text.toString()
            Log.d("MainResgitrar", "Nombre: $nombre")
            Log.d("MainResgitrar", "Apellido: $apellido")
            Log.d("MainResgitrar", "Email: $email")
            Log.d("MainResgitrar", "Password: $password")
            //Mostrar por consola los datos Seleccionados del spinner
            Log.d("MainResgitrar", "Edad Seleccionada: $selectedEdad")
            Log.d("MainResgitrar", "Año Seleccionado: $selectedAnio")*/

        }
    }
}