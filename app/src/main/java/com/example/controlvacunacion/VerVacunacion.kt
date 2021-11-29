package com.example.controlvacunacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.controlvacunacion.vacunacion.AppDataBase
import com.example.controlvacunacion.vacunacion.Vacunacion
import kotlinx.android.synthetic.main.activity_editar_vacunacion.*
import kotlinx.android.synthetic.main.activity_ver_vacunacion.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VerVacunacion : AppCompatActivity() {
    private lateinit var db : AppDataBase
    private lateinit var vacunacion : Vacunacion
    private lateinit var vacunacionLiveData : LiveData<Vacunacion>
    var idVac : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_vacunacion)

        db = AppDataBase.getBaseDatos(this)

        idVac = intent.getIntExtra("idVac", 0)

        vacunacionLiveData = db.vacunaciones().obtenerVacunacion(idVac)

        vacunacionLiveData.observe(this, Observer {
            vacunacion = it
            tvVerEdad.text = vacunacion.edad.toString()
            tvVerOcupacion.text = vacunacion.ocupacion
            tvVerDepartamento.text = vacunacion.departamento
            tvVerVacuna.text = vacunacion.vacuna
            tvVerSintomas.text = vacunacion.sintomas
            tvVerPais.text = vacunacion.pais
        })

        /*btnEditV.setOnClickListener {
            val intent = Intent(this, EditarVacunacion::class.java)
            intent.putExtra("idVac", idVac)
            startActivity(intent)
        }

        btnBorrarV.setOnClickListener {
            vacunacionLiveData.removeObservers(this)
            CoroutineScope(Dispatchers.IO).launch {
                db.vacunaciones().eliminarVacunacion(vacunacion)
                this@VerVacunacion.finish()
            }
        }*/

    }
}