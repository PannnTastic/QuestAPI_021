package com.example.aplikasimysql.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.aplikasimysql.modeldata.DetailSiswa
import com.example.aplikasimysql.modeldata.UIStateSiswa
import com.example.aplikasimysql.modeldata.toSiswa
import com.example.aplikasimysql.repositori.RepositoriDataSiswa
import retrofit2.Response

class EntryViewModel (private val repositoriDataSiswa: RepositoriDataSiswa)
    : ViewModel(){
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set
    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa)
    : Boolean{
        return with(uiState){
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa){
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    suspend fun addSiswa(){
        if (validasiInput()){
            val sip: Response<Void> =repositoriDataSiswa.insertSiswa(uiStateSiswa.detailSiswa.toSiswa())
            if (sip.isSuccessful){
                println("Sukses Tambah Data : ${sip.message()}")
            }else{
                println("Gagal Tambah Data : ${sip.message()}")
            }
        }
    }
}