package com.vargas.tarea2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vargas.tarea2.DatasClass.Item
import com.vargas.tarea2.DatasClass.Seccion
import com.vargas.tarea2.R

class MenuPrincipalViewModel : ViewModel() {

    private val _secciones = MutableLiveData<List<Seccion>>();
    val secciones: LiveData<List<Seccion>> = _secciones;
    init {
        loadItems();
    }

    private fun loadItems() {
        val secciones = listOf(
            Seccion("Peliculas de Terror",
                listOf(
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1)
                )
            ),
            Seccion("Peliculas de Ciencia Ficcion",
                listOf(
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                )
            ),
            Seccion("Peliculas de Historia",
                listOf(
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1)
                )
            ),
            Seccion("Documentales",
                listOf(
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1)
                )
            ),
            Seccion("Peliculas Animadas",
                listOf(
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1)
                )
            ),
            Seccion("Peliculas de Fantasia",
                listOf(
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1)
                )
            ),
            Seccion("Series de Crimen",
                listOf(
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1)
                )
            ),
            Seccion("Series de Misterio",
                listOf(
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1)
                )
            ),
            Seccion("Series de Animes",
                listOf(
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                )
            ),
            Seccion("Peliculas Nominadas al Oscar",
                listOf(
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                    Item(R.drawable.logotipodepeliculas1),
                )
            ),
        )
        _secciones.value = secciones;
    }
}