package com.trabajoIntegrador.datos.geografy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "usuarios_tabla")
data class Usuario(
    @ColumnInfo(name = "usuario") var usuario:String,
    @ColumnInfo(name = "correo") var correo:String,
    @ColumnInfo(name = "contrasenia") var pass:String,
){
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}
