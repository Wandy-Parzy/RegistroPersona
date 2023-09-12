package com.example.registropersona.data.repository

import com.example.registropersona.data.local.entity.PersonaEntity
import com.example.registropersona.data.local.dao.PersonaDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonaRepository @Inject constructor(
    private  val personaDao: PersonaDao
) {
    suspend fun insert(persona: PersonaEntity) {
        return personaDao.insert(persona)
    }

    fun getList(): Flow<List<PersonaEntity>> = personaDao.getList()
}