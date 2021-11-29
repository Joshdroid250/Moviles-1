package com.example.controlvacunacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.controlvacunacion.vacunacion.AdapterVacunacion
import com.example.controlvacunacion.vacunacion.AppDataBase
import com.example.controlvacunacion.vacunacion.Vacunacion
import kotlinx.android.synthetic.main.activity_lista_vacunacion.*

class ListaVacunacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_vacunacion)

        iniciar()
    }

    fun iniciar(){
        var listaVac = emptyList<Vacunacion>()
        val bd = AppDataBase.getBaseDatos(this)

        bd.vacunaciones().obtenerRegistros().observe(this, {
            listaVac = it

            val adapter = AdapterVacunacion(this, listaVac)

            ListViewVac.adapter = adapter
        })

        ListViewVac.setOnItemClickListener(){ parent, view, position, id ->
            val intent = Intent(this, VerVacunacion::class.java)
            intent.putExtra("idVac", listaVac[position].id)
            startActivity(intent)
        }

        btnAgregar.setOnClickListener{
            val intent = Intent(this, Vacunaciones::class.java)
            startActivity(intent)
        }


    }

}