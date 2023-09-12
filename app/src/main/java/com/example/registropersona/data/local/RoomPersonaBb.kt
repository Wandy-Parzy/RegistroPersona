package com.example.registropersona.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.registropersona.data.local.entity.PersonaEntity
import com.example.registropersona.data.local.dao.PersonaDao

@Database(
    entities = [
        PersonaEntity::class,
    ],
    version = 1
)
abstract class RoomPersonaBb: RoomDatabase() {
    abstract val personaDao : PersonaDao
}