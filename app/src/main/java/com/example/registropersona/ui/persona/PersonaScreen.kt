package com.example.registropersona.ui.persona

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.registropersona.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.twotone.Check
import androidx.compose.material.icons.twotone.Error
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.KeyboardArrowDown
//import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
//import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.registropersona.data.local.entity.PersonaEntity
import java.util.*

@Composable
fun PersonaScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        PersonaInterface()
    }
}

@Preview(showSystemUi = true)
@Composable
fun PersonaInterface(viewModel: PersonaViewModel = hiltViewModel()) {
    Column(modifier = Modifier) {
        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "Registro de personas", fontSize = 25.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.padding(14.dp))
        NombreCase(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(1.dp))
        TelefonoCase(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(1.dp))
        CelularCase(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(1.dp))
        EmailCase(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(1.dp))
        DirecciónCase(Modifier.align(Alignment.CenterHorizontally))
        FechaCase(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            context = LocalContext.current,
            viewModel = viewModel
        )
        Spacer(modifier = Modifier.padding(1.dp))
        SelectOcupacionesCase(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(8.dp))
        ButtonGuardar(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(8.dp))
        val uiState by viewModel.uiState.collectAsState()
        PersonaListScreen(uiState.personaList)
    }
}

//Campo nombre
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NombreCase(modifier: Modifier, viewModel: PersonaViewModel = hiltViewModel()) {

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    OutlinedTextField(
        value = viewModel.nombre,
        onValueChange = viewModel::onNombreChanged,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(5.dp)
            .onGloballyPositioned { coordinates ->
                textfieldSize = coordinates.size.toSize()
            },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
                modifier = Modifier
                    .size(33.dp)
                    .padding(4.dp)
            )
        },
        label = { Text("Nombre") },
        isError = viewModel.nombreError.isNotBlank(),
        trailingIcon = {
            if (viewModel.nombreError.isNotBlank()) {
                Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
            }else if(viewModel.nombre.isNotEmpty())
            {
                Icon(imageVector = Icons.TwoTone.Check, contentDescription = "success")
            }
        }
    )
    if (viewModel.nombreError.isNotBlank()) {
        Text(
            text = viewModel.nombreError,
            color = MaterialTheme.colorScheme.error
        )
    }

}

//Campo telefono
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelefonoCase(modifier: Modifier, viewModel: PersonaViewModel = hiltViewModel()) {

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    OutlinedTextField(
        value = viewModel.telefono,
        onValueChange = { viewModel.telefono = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(5.dp)
            .onGloballyPositioned { coordinates ->
                textfieldSize = coordinates.size.toSize()
            },
        label = { Text("Telefono", fontSize = 19.sp) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal
        )
    )
}

//Campo celular
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CelularCase(modifier: Modifier, viewModel: PersonaViewModel = hiltViewModel()) {

    var expanded by remember { mutableStateOf(false) }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    OutlinedTextField(
        value = viewModel.celular,
        onValueChange = { viewModel.celular = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(5.dp)
            .onGloballyPositioned { coordinates ->
                textfieldSize = coordinates.size.toSize()
            },
        label = { Text("Celular", fontSize = 19.sp) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal
        )
    )
}
//Campo email
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailCase(modifier: Modifier, viewModel: PersonaViewModel = hiltViewModel()) {

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    OutlinedTextField(
        value = viewModel.email,
        onValueChange = { viewModel.email = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(5.dp)
            .onGloballyPositioned { coordinates ->
                textfieldSize = coordinates.size.toSize()
            },
        label = { Text("Email", fontSize = 19.sp) }
    )
}
//Campo direccion
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DirecciónCase(modifier: Modifier, viewModel: PersonaViewModel = hiltViewModel()) {

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    OutlinedTextField(
        value = viewModel.direccion,
        onValueChange = { viewModel.direccion = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(5.dp)
            .onGloballyPositioned { coordinates ->
                textfieldSize = coordinates.size.toSize()
            },
        label = { Text("Dirección", fontSize = 19.sp) }
    )
}

//Campo fecha
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FechaCase(
    modifier: Modifier,
    context: Context,
    viewModel: PersonaViewModel = hiltViewModel()
) {
    var fecha by remember { mutableStateOf("") }

    val mDatePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, anio, mes, dia ->
                fecha = "${dia}/${mes + 1}/${anio}"
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
    }

    Row {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(5.dp),
            value = fecha,
            onValueChange = { viewModel.fecha = it },
            readOnly = true,
            enabled = false,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                        .clickable { mDatePickerDialog.show() }
                )
            },
            label = { Text(text = "Fecha de nacimiento", fontSize = 19.sp) }
        )
    }
}

//Lista de ocupaciones
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectOcupacionesCase(modifier: Modifier) {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Ingeniero", "Arquitecto", "Enfermera/o", "Pintor")

    var selectedText by remember { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    OutlinedTextField(
        value = selectedText,
        onValueChange = { selectedText = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(5.dp)
            .onGloballyPositioned { coordinates ->

                textfieldSize = coordinates.size.toSize()
            },
        label = { Text("Selecciona ocupacion", fontSize = 19.sp) },
        readOnly = true, enabled = false,
        trailingIcon = {
            Icon(icon, "contentDescription",
                modifier.clickable { expanded = !expanded })
        }
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
            .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
    ) {
        suggestions.forEach { label ->

        }
    }
}

//Boton gurdar
@Composable
fun ButtonGuardar(modifier: Modifier, viewModel: PersonaViewModel = hiltViewModel()) {

    var selectedText by remember { mutableStateOf("") }

    Button(
        onClick = { viewModel.Guardar() },
        modifier = Modifier
            .height(67.dp)
            .padding(5.dp)
            .fillMaxSize()
            .width(50.dp),
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF156619),
            contentColor = Color.White,
            disabledContentColor = Color.White,
        ),
    ) {
        Image(

            painter = painterResource(id = R.drawable.check),
            contentDescription = "Header",
            modifier = modifier
        )
        Text(text = " Guardar", modifier = modifier, fontSize = 20.sp)
    }
}

//lista de personas
@Composable
private fun PersonaListScreen(personaList: List<PersonaEntity>) {
    LazyColumn {
        items(personaList) { persona ->
            PersonaRow(persona)
        }
    }
}
//lista de personas
@Composable
private fun PersonaRow(persona: PersonaEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        Divider(Modifier.fillMaxWidth())

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = persona.nombre,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )


            Spacer(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(Color.Gray)
            )

            Text(
                text = persona.telefono,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )

            Spacer(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(Color.Gray)
            )

            Text(
                text = persona.celular,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )

            Spacer(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(Color.Gray)
            )

            Text(
                text = persona.email,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )

            Spacer(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(Color.Gray)
            )

            Text(
                text = persona.direccion,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )

            Spacer(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(Color.Gray)
            )

            Text(
                text = persona.fecha,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )
        }
        Divider(Modifier.fillMaxWidth())
    }
}
