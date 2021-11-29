package com.example.controlvacunacion


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.controlvacunacion.databinding.ActivityVacunacionesBinding
import com.example.controlvacunacion.vacunacion.AppDataBase
import com.example.controlvacunacion.vacunacion.Vacunacion
import kotlinx.android.synthetic.main.activity_vacunaciones.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Vacunaciones : AppCompatActivity(){
    private lateinit var binding: ActivityVacunacionesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityVacunacionesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val ocupacion = resources.getStringArray(R.array.Ocupacion)
        val adapterOcup = ArrayAdapter(
            this,
            R.layout.lista_ocupacion,
            ocupacion
        )
        with(binding.etOcupacion){
            setAdapter(adapterOcup)
        }

        val departamento = resources.getStringArray(R.array.Departamento)
        val adapterDep = ArrayAdapter(
            this,
            R.layout.lista_departamentos,
            departamento
        )
        with(binding.etDepartamento){
            setAdapter(adapterDep)
        }

        val vacuna = resources.getStringArray(R.array.Vacunas)
        val adapterVac = ArrayAdapter(
            this,
            R.layout.lista_vacuna,
            vacuna
        )
        with(binding.etVacuna){
            setAdapter(adapterVac)
        }

        val pais = resources.getStringArray(R.array.Pais)
        val adapterPais = ArrayAdapter(
            this,
            R.layout.lista_pais,
            pais
        )
        with(binding.etPais){
            setAdapter(adapterPais)
        }

        guardar()
    }



    private fun guardar() {
        btnGuardar.setOnClickListener {
            val edad = etEdad.text.toString().toInt()
            val ocupacion = etOcupacion.text.toString()
            val departamento = etDepartamento.text.toString()
            val vacuna = etVacuna.text.toString()
            val sintomas = etSintomas.text.toString()
            val pais = etPais.text.toString()

            val vacunado = Vacunacion(edad, ocupacion, departamento, vacuna, sintomas, pais)
            val db = AppDataBase.getBaseDatos(this)
            CoroutineScope(Dispatchers.IO).launch {
                db.vacunaciones().insertarVacunacion(vacunado)
                this@Vacunaciones.finish()
            }
        }
    }

}