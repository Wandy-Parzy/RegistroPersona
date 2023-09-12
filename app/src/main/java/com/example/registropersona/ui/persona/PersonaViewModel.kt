package com.example.registropersona.ui.persona


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registropersona.data.local.entity.PersonaEntity
import com.example.registropersona.data.repository.PersonaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PersonaUiState(
    val personaList: List<PersonaEntity> = emptyList()
)

@HiltViewModel
class PersonaViewModel @Inject constructor(
    private val personaRepository: PersonaRepository
) : ViewModel() {
    var nombre by mutableStateOf("")
    var telefono by mutableStateOf("")
    var celular by mutableStateOf("")
    var email by mutableStateOf("")
    var direccion by mutableStateOf("")
    var fecha by mutableStateOf("2023-09-12")



    var nombreError by mutableStateOf("")


    var uiState = MutableStateFlow(PersonaUiState())
        private set
    init {
        getListPersona()
    }
    fun getListPersona() {
        viewModelScope.launch(Dispatchers.IO) {
            personaRepository.getList().collect{lista ->
                uiState.update {
                    it.copy(personaList = lista)
                }
            }
        }
    }

    fun onNombreChanged(nombre: String) {
        this.nombre =  nombre
        Validation()
    }

    fun Guardar() {
       if(Validation())
           return

        val persona = PersonaEntity(
            nombre = nombre,
            telefono = telefono,
            celular = celular,
            email = email,
            direccion = direccion,
            fecha = fecha
        )
        viewModelScope.launch(Dispatchers.IO) {
            personaRepository.insert(persona)
            Limpiar()
        }
    }
    private fun Validation(): Boolean {

        var Error = false

        nombreError = ""

        if (nombre.isBlank()) {
            nombreError = "Favor ingresar el nombre"
            Error = true
        }else{
            Error
        }
        return Error
    }

    private fun Limpiar() {
        nombre = ""
        telefono = ""
        celular = ""
        email = ""
        direccion = ""
        fecha = "2023-09-12"
    }
}
