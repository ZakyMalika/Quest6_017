package com.example.mvvm_first.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mvvm_first.model.Siswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SiswaViewModel : ViewModel() {
    private val _statusUI = MutableStateFlow(Siswa())

    val statusUI: StateFlow<Siswa> = _statusUI.asStateFlow()

    fun setSiswa(ls: MutableList<String>){
        _statusUI.update { statusSaatIni ->
            statusSaatIni.copy(
                name = ls[0],
                gender = ls[1],
                alamat = ls[2]
            )
        }
    }



}