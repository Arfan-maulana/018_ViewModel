package com.example.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodel.Data.DataForm
import com.example.viewmodel.Data.DataSource.jenis
import com.example.viewmodel.Data.DataSource.status
import com.example.viewmodel.ui.theme.ViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewModelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TampilLayout()
                }
            }
        }
    }
}

@Composable
fun TampilLayout(
    modifier: Modifier = Modifier
){
    Card(modifier = Modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)){
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
        ) {
            TampilText()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TampilText(cobaViewModel: CobaViewModel = viewModel()){

    var textForm by remember { mutableStateOf("") }
    var phoneForm by remember { mutableStateOf("")}
    var alamatForm by remember { mutableStateOf("")}
    var emailForm by remember { mutableStateOf("")}

    val context = LocalContext.current
    val dataForm: DataForm
    val uiState by cobaViewModel.uiState.collectAsState()
    dataForm = uiState

    OutlinedTextField(
        value = textForm,
        onValueChange = {textForm = it},
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        label = { Text(text = "Nama Lengkap")},
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    )
    OutlinedTextField(
        value = phoneForm,
        onValueChange = {phoneForm = it},
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        label = { Text(text = "Nomor Telepon")},
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    )
    OutlinedTextField(
        value = alamatForm,
        onValueChange = {alamatForm = it},
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        label = { Text(text = "Alamat")},
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    )
    OutlinedTextField(
        value = emailForm,
        onValueChange = {emailForm = it},
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        label = { Text(text = "Email")},
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    )

    SelectJK(options = jenis.map { id -> context.resources.getString(id) },
        onSelectedChanged = {
            cobaViewModel.setJenisK(it)
        })
    PilihSK(options = status.map {id -> context.resources.getString(id) },
        onSelectedChanged = {
            cobaViewModel.setPilihSK(it)
        }
    )

    Button(modifier = Modifier.fillMaxWidth(),
        onClick = {cobaViewModel.BacaData(textForm,phoneForm,alamatForm,emailForm,dataForm.sex)
        }
    ) {
        Text(text = stringResource(R.string.submit),
            fontSize = 16.sp)

    }
    Spacer(modifier = Modifier.height(150.dp))
    TextHasil(
        namanya = cobaViewModel.namaUsr,
        telponnya =cobaViewModel.Notlp ,
        jenisnya = cobaViewModel.JenisKl,
        alamatnya = cobaViewModel.alamatUsr,
        emailnya = cobaViewModel.emailUsr,
        statusnya = cobaViewModel.statusUsr
    )

}
@Composable
fun TextHasil(namanya:String,telponnya:String,jenisnya:String,alamatnya:String,emailnya:String,statusnya:String){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ){
        Text(text = "Nama : " + namanya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
        Text(text = "Telepon : " + telponnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
        Text(text = "Email : " + emailnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
        Text(text = "Jenis Kelamin : " + jenisnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
        Text(text = "Status :" + statusnya,
        modifier = Modifier)

        Text(text = "Alamat : " + alamatnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))

    }

}

@Composable
fun SelectJK(
    options: List<String>,
    onSelectedChanged: (String) -> Unit = {}) {

    var selectedValue by rememberSaveable {
        mutableStateOf("")
    }
    Row(modifier = Modifier.padding(16.dp)) {
        options.forEach { item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectedChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectedChanged(item)
                    }
                )
                Text(item)
            }
        }
    }

}
@Composable
fun PilihSK(
    options: List<String>,
    onSelectedChanged: (String) -> Unit = {}
) {
    var selectedValuee by rememberSaveable {
        mutableStateOf("")
    }
    Row(modifier = Modifier.padding(16.dp)) {
        options.forEach { item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValuee == item,
                    onClick = {
                        selectedValuee = item
                        onSelectedChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedValuee == item,
                    onClick = {
                        selectedValuee = item
                        onSelectedChanged(item)
                    }
                )
                Text(item)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ViewModelTheme {
       TampilLayout()
    }
}
