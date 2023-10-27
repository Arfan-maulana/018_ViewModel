package com.example.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.viewmodel.Data.DataForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CobaViewModel : ViewModel() {
    var namaUsr: String by mutableStateOf("")
        private set
    var Notlp: String by mutableStateOf("")
        private set
    var JenisKl: String by mutableStateOf("")
        private set
    var alamatUsr: String by mutableStateOf("")
        private set
    var emailUsr: String by mutableStateOf("")
        private set
    var statusUsr: String by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow(DataForm())
    val uiState: StateFlow<DataForm> = _uiState.asStateFlow()

    fun BacaData(nm: String, tlp: String, jk: String, al: String, em: String) {
        namaUsr = nm
        Notlp = tlp
        JenisKl = jk
        alamatUsr = al
        emailUsr = em
    }

    fun setPilihSK(pilihSK: String){
        _uiState.update { saatini -> saatini.copy(statuss = pilihSK) }
    }

    fun setJenisK(pilihJK: String) {
        _uiState.update { currentState ->
            currentState.copy(sex = pilihJK)
        }
    }
}