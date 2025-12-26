package com.example.aplikasimysql.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasimysql.modeldata.DetailSiswa
import com.example.aplikasimysql.modeldata.UIStateSiswa
import com.example.aplikasimysql.modeldata.toSiswa
import com.example.aplikasimysql.modeldata.toUiStateSiswa
import com.example.aplikasimysql.repositori.RepositoriDataSiswa
import com.example.aplikasimysql.uicontroller.route.DestinasiEdit
import kotlinx.coroutines.launch
import retrofit2.Response

class EditViewModel(savedStateHandle: SavedStateHandle, private val repositoriDataSiswa: RepositoriDataSiswa) : ViewModel() {
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiEdit.itemIdArg])

    init{
        viewModelScope.launch {
        uiStateSiswa =repositoriDataSiswa.getSatuSiswa(idSiswa).toUiStateSiswa()
        }

    }

    fun updateUiState(detailSiswa: DetailSiswa){
        uiStateSiswa =
            UIStateSiswa(detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    private fun validasiInput(uiState: DetailSiswa): Boolean{
        return with(uiState){
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    suspend fun editSiswa(){
        if (validasiInput(uiStateSiswa.detailSiswa)){
            val call: Response<Void> = repositoriDataSiswa.updateSiswa(idSiswa, uiStateSiswa.detailSiswa.toSiswa())
            if (call.isSuccessful){
                println("Sukses Mengupdate Siswa : ${call.message()}")
            }
            else {
                println("Gagal Mengupdate Siswa : ${call.message()}")
            }
        }
    }
}