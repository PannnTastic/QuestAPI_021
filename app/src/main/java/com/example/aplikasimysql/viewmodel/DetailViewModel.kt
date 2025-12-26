package com.example.aplikasimysql.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasimysql.modeldata.DataSiswa
import com.example.aplikasimysql.repositori.RepositoriDataSiswa
import com.example.aplikasimysql.uicontroller.route.DestinasiDetail
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

sealed interface StatusUIDetail {
    data class Success (val satuSiswa: DataSiswa) : StatusUIDetail
    object Error: StatusUIDetail
    object Loading: StatusUIDetail
}
class DetailViewModel (savedStateHandle: SavedStateHandle,private val repositoriDataSiswa: RepositoriDataSiswa):
    ViewModel(){
    private val idSiswa: String = checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])
    var statusUIDetail: StatusUIDetail by mutableStateOf(StatusUIDetail.Loading)
        private set

    init {
        getSatuSiswa()
    }

    fun getSatuSiswa(){
        viewModelScope.launch {
            statusUIDetail = StatusUIDetail.Loading
            statusUIDetail = try {
                StatusUIDetail.Success(satuSiswa = repositoriDataSiswa.getSatuSiswa(idSiswa))
            }
            catch (e: IOException){
                StatusUIDetail.Error
            }
            catch (e: HttpException){
                StatusUIDetail.Error
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    suspend fun hapusSiswa(){
        val response: Response<Void> = repositoriDataSiswa.deleteSiswa(idSiswa)

        if (response.isSuccessful){
            println("Sukses Menghapus Siswa : ${response.message()}")
        }
        else{
            println("Gagal Menghapus Siswa : ${response.message()}")
        }
    }
}