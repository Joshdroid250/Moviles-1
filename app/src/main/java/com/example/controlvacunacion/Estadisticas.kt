package com.example.controlvacunacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.example.controlvacunacion.vacunacion.AppDataBase
import com.example.controlvacunacion.vacunacion.Vacunacion
import kotlinx.android.synthetic.main.activity_editar_vacunacion.*
import kotlinx.android.synthetic.main.activity_estadisticas.*

class Estadisticas : AppCompatActivity() {
    private lateinit var db : AppDataBase
    private var Contador : Int = 0
    private lateinit var vacunacionLiveDataTotal : LiveData<Int>

    private var contadorJJ : Int = 0
    private lateinit var janssenLiveData: LiveData<Int>
    private var contadorSputL : Int = 0
    private lateinit var sputniklLiveData: LiveData<Int>
    private var contadorSputV : Int = 0
    private lateinit var sputnikVLiveData: LiveData<Int>
    private var contadorAstra : Int = 0
    private lateinit var astraLiveData: LiveData<Int>
    private var contadorModerna : Int = 0
    private lateinit var modernaLiveData: LiveData<Int>
    private var contadorPfizer : Int = 0
    private lateinit var pfizerLiveData: LiveData<Int>
    private var contadorSobe : Int = 0
    private lateinit var soberanaLiveData: LiveData<Int>
    private var chart: AnyChartView? = null
    private var cantidad = mutableListOf(0,0,0,0,0,0,0)
    private val vacunas  = listOf("Sputnik V", "Sputnik Light" , "Astrazeneca", "Janssen", "Pfizer", "Moderna", "Soberana")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadisticas)

        chart = findViewById(R.id.pieChart)

        mostrarDatos()
    }

    private fun mostrarDatos() {
        db = AppDataBase.getBaseDatos(this)
        vacunacionLiveDataTotal = db.vacunaciones().getCountTotal()
        vacunacionLiveDataTotal.observe(this, Observer {
            Contador = it
            tvTotal.text = Contador.toString()
        })

        sputnikVLiveData = db.vacunaciones().getCountSputnikV()
        sputnikVLiveData.observe(this, Observer {
            contadorSputV = it
            cantidad[0] = contadorSputV
            configChartView()
        })
        sputniklLiveData = db.vacunaciones().getCountSputnikL()
        sputniklLiveData.observe(this, Observer {
            contadorSputL = it
            cantidad[1] = contadorSputL
            configChartView()
        })
        astraLiveData = db.vacunaciones().getCountAstra()
        astraLiveData.observe(this, Observer {
            contadorAstra = it
            cantidad[2] = contadorAstra
            configChartView()
        })
        janssenLiveData = db.vacunaciones().getCountJanssen()
        janssenLiveData.observe(this, Observer {
            contadorJJ = it
            cantidad[3] = contadorJJ
            configChartView()
        })
        pfizerLiveData = db.vacunaciones().getCountPfizer()
        pfizerLiveData.observe(this, Observer {
            contadorPfizer = it
            cantidad[4] = contadorPfizer
            configChartView()
        })
        modernaLiveData = db.vacunaciones().getCountModerna()
        modernaLiveData.observe(this, Observer {
            contadorModerna = it
            cantidad[5] = contadorModerna
            configChartView()
        })
        soberanaLiveData = db.vacunaciones().getCountSoberana()
        soberanaLiveData.observe(this, Observer {
            contadorSobe = it
            cantidad[6] = contadorSobe
            configChartView()
        })

    }

    private fun configChartView() {
        val pie : Pie = AnyChart.pie()

        val dataPieChart : MutableList<DataEntry> = mutableListOf()

        for ( index in cantidad.indices){
            dataPieChart.add(ValueDataEntry(vacunas.elementAt(index), cantidad.elementAt(index)))
        }

        pie.data(dataPieChart)
        pie.title("Datos de vacunacion")
        chart!!.setChart(pie)
    }
}