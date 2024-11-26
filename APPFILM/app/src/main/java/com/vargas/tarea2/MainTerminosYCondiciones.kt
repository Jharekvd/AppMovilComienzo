package com.vargas.tarea2

import android.os.Bundle
import android.text.Html
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vargas.tarea2.databinding.ActivityMainBinding
import com.vargas.tarea2.databinding.ActivityMainTerminosYcondicionesBinding

class MainTerminosYCondiciones : AppCompatActivity() {
    private lateinit var binding: ActivityMainTerminosYcondicionesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainTerminosYcondicionesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView3.apply {
            text = Html.fromHtml(
                resources.getStringArray(R.array.terminos).joinToString("\n\n"),
                Html.FROM_HTML_MODE_COMPACT
            )
        }
    }
}