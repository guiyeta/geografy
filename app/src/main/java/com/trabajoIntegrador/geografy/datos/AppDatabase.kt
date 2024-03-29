package com.trabajoIntegrador.datos.geografy

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    companion object{

        private var INSTANCIA: AppDatabase?= null

        fun getDatabase(contexto: Context) : AppDatabase{
            if(INSTANCIA == null){
                synchronized(this){
                    INSTANCIA = Room.databaseBuilder(
                        contexto, AppDatabase::class.java, "base_app_geografiapp")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCIA!!
        }


    }
}