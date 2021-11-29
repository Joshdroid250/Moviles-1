package com.example.controlvacunacion.vacunacion

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Vacunacion::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun vacunaciones(): VacunacionDAO

    companion object{
        @Volatile
        private var INSTANCE : AppDataBase? = null

        fun getBaseDatos(context: Context): AppDataBase{
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_DataBase"
                ).build()

                INSTANCE = instance

                return instance
            }
        }
    }
}