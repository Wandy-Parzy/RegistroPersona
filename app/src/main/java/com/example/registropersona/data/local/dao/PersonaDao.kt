package com.example.registropersona.data.local.dao

import androidx.room.*
import com.example.registropersona.data.local.entity.PersonaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(personaEntity: PersonaEntity)

    @Query(
        """
        SELECT * 
        FROM Persona
        WHERE PersonaId=:personaId
        LIMIT 1
    """
    )

    suspend fun replace(personaId: Int): PersonaEntity?

    @Query("SELECT * FROM Persona")
    fun getList(): Flow<List<PersonaEntity>>
}