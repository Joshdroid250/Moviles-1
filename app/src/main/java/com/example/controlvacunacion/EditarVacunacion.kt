package com.example.controlvacunacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.controlvacunacion.databinding.ActivityEditarVacunacionBinding
import com.example.controlvacunacion.vacunacion.AppDataBase
import com.example.controlvacunacion.vacunacion.Vacunacion
import kotlinx.android.synthetic.main.activity_editar_vacunacion.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EditarVacunacion : AppCompatActivity() {
    var idVac : Int = 0
    private lateinit var db : AppDataBase
    private lateinit var vacunacion : Vacunacion
    private lateinit var vacunacionLiveData : LiveData<Vacunacion>

    lateinit var binding: ActivityEditarVacunacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditarVacunacionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val ocupacion = resources.getStringArray(R.array.Ocupacion)
        val adapterOcup = ArrayAdapter(
            this,
            R.layout.lista_ocupacion,
            ocupacion
        )
        with(binding.etEditOcupacion){
            setAdapter(adapterOcup)
        }

        val departamento = resources.getStringArray(R.array.Departamento)
        val adapterDep = ArrayAdapter(
            this,
            R.layout.lista_departamentos,
            departamento
        )
        with(binding.etEditDepartamento){
            setAdapter(adapterDep)
        }

        val vacuna = resources.getStringArray(R.array.Vacunas)
        val adapterVac = ArrayAdapter(
            this,
            R.layout.lista_vacuna,
            vacuna
        )
        with(binding.etEditVacuna){
            setAdapter(adapterVac)
        }

        val pais = resources.getStringArray(R.array.Pais)
        val adapterPais = ArrayAdapter(
            this,
            R.layout.lista_pais,
            pais
        )
        with(binding.etEditPais){
            setAdapter(adapterPais)
        }

        mostrarVac()

        binding.btnEditarVac.setOnClickListener {
            val edad = binding.etEditEdad.text.toString().toInt()
            val ocup = binding.etEditOcupacion.text.toString()
            val dept = binding.etEditDepartamento.text.toString()
            val vac = binding.etEditVacuna.text.toString()
            val sint = binding.etEditSintomas.text.toString()
            val pais = binding.etEditPais.text.toString()

            val vacunado = Vacunacion(edad, ocup, dept, vac, sint, pais)

            db = AppDataBase.getBaseDatos(this)

            if (idVac != null){
                CoroutineScope(Dispatchers.IO).launch {

                    vacunado.id = idVac as Int
                    db.vacunaciones().actualizarVacunacion(vacunado)

                    this@EditarVacunacion.finish()
                }
            }
        }
    }

    private fun mostrarVac(){
        idVac = intent.getIntExtra("idVac", 0)

        db = AppDataBase.getBaseDatos(this)
        vacunacionLiveData = db.vacunaciones().obtenerVacunacion(idVac)

        vacunacionLiveData.observe(this, Observer {
            vacunacion = it
            etEditEdad.setText(vacunacion.edad.toString())
            etEditOcupacion.setText(vacunacion.ocupacion)
            etEditDepartamento.setText(vacunacion.departamento)
            etEditVacuna.setText(vacunacion.vacuna)
            etEditSintomas.setText(vacunacion.sintomas)
            etEditPais.setText(vacunacion.pais)
        })
    }
}