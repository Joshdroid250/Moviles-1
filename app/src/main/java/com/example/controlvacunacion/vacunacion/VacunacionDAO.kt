package com.example.controlvacunacion.vacunacion

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VacunacionDAO {
    @Query("Select * from Vacunacion")
    fun obtenerRegistros(): LiveData<List<Vacunacion>>

    @Query("Select * from Vacunacion where id = :id")
    fun obtenerVacunacion(id: Int): LiveData<Vacunacion>

    @Insert
    fun insertarVacunacion(vararg vacunacion: Vacunacion)

    @Update
    fun actualizarVacunacion(vacunacion: Vacunacion)

    @Delete
    fun eliminarVacunacion(vacunacion: Vacunacion)

    @Query("Select count(*)  from vacunacion")
    fun getCountTotal() : LiveData<Int>

    @Query("SELECT count(*) FROM vacunacion where vacuna like 'Janssen';")
    fun getCountJanssen() : LiveData<Int>

    @Query("SELECT count(*) FROM vacunacion where vacuna like 'Sputnik V';")
    fun getCountSputnikV() : LiveData<Int>

    @Query("SELECT count(*) FROM vacunacion where vacuna like 'Sputnik Light';")
    fun getCountSputnikL() : LiveData<Int>

    @Query("SELECT count(*) FROM vacunacion where vacuna like 'Pfizer';")
    fun getCountPfizer() : LiveData<Int>

    @Query("SELECT count(*) FROM vacunacion where vacuna like 'Astrazeneca';")
    fun getCountAstra() : LiveData<Int>

    @Query("SELECT count(*) FROM vacunacion where vacuna like 'Moderna';")
    fun getCountModerna() : LiveData<Int>

    @Query("SELECT count(*) FROM vacunacion where vacuna like 'Soberana';")
    fun getCountSoberana() : LiveData<Int>
}