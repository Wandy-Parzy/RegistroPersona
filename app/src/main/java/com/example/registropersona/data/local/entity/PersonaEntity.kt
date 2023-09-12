package com.example.registropersona.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.format.DateTimeFormatter

@Entity(tableName = "Persona")
data class PersonaEntity (
    @PrimaryKey(autoGenerate = true)
    val personaid : Int?=null,
    var nombre : String,
    var telefono:String ,
    var celular: String,
    var email: String,
    var direccion: String,
    var fecha: String = "2023-09-12"

)