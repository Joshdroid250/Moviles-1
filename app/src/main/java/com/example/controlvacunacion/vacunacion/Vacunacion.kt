package com.example.controlvacunacion.vacunacion

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "Vacunacion")
data class Vacunacion(

    @NonNull @ColumnInfo(name = "edad") val edad: Int
    , @NonNull @ColumnInfo(name = "ocupacion") val ocupacion: String
    , @NonNull @ColumnInfo(name = "departamente") val departamento: String
    , @NonNull @ColumnInfo(name = "vacuna") val vacuna: String
    , @NonNull @ColumnInfo(name = "sintomas") val sintomas: String
    , @NonNull @ColumnInfo(name = "pais") val pais: String
    , @PrimaryKey (autoGenerate = true) var id: Int = 0
): Serializable