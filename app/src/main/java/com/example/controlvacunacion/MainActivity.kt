package com.example.controlvacunacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.controlvacunacion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnRegsitro.setOnClickListener(){
            abrirLista()
        }

        binding.btnStats.setOnClickListener() {
            abrirEstadisticas()
        }
    }

    private fun abrirEstadisticas() {
        startActivity(Intent(this, Estadisticas::class.java))
    }

    fun abrirLista(){
        startActivity(Intent(this, ListaVacunacion::class.java))
    }
}
