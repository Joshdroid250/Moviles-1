package com.example.controlvacunacion.vacunacion

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.controlvacunacion.R
import kotlinx.android.synthetic.main.item_vacunacion.view.*

class AdapterVacunacion(private val mContext: Context, private val listaVacunacion: List<Vacunacion>):ArrayAdapter<Vacunacion>(mContext,0, listaVacunacion) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_vacunacion, parent, false)

        val vacunados = listaVacunacion[position]

        layout.TvVacuna.text = vacunados.vacuna
        layout.TvDepartamento.text = vacunados.departamento
        layout.TvOcupacion.text = vacunados.ocupacion
        layout.TvEdad.text = vacunados.edad.toString()
        layout.TvPais.text = vacunados.pais
        layout.TvSintomas.text = vacunados.sintomas


        return layout
    }
}