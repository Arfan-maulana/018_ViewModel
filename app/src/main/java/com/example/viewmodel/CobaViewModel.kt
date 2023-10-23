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

class CobaViewModel : ViewModel(){
    var namaUsr : String by mutableStateOf("")
        private set
    var Notlp : String by mutableStateOf("")
        private set
    var JenisKl : String by mutableStateOf("")
        private set
    var alamatUsr : String by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow(DataForm())
    val uiState: StateFlow<DataForm> = _uiState.asStateFlow()

    fun BacaData(nm:String,tlp:String,jk:String,al:String){
        namaUsr=nm
        Notlp=tlp
        JenisKl=jk
        alamatUsr=al
    }

    fun setJenisK(pilihJK:String){
        _uiState.update {saatIni -> saatIni.copy(sex = pilihJK}
    }
}
