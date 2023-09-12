package com.example.registropersona.di

import android.content.Context
import androidx.room.Room
import  com.example.registropersona.data.local.RoomPersonaBb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // TODO: Inyectar la base de datos
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): RoomPersonaBb {
        return Room.databaseBuilder(
            context,
            RoomPersonaBb::class.java,
            "PersonaDb.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    // TODO: Inyectar el DAO
    @Singleton
    @Provides
    fun providesPersonaDao(db: RoomPersonaBb) = db.personaDao
}