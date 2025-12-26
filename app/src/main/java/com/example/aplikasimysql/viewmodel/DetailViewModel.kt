package com.example.aplikasimysql.viewmodel

import com.example.aplikasimysql.modeldata.DataSiswa

sealed interface StatusUIDetail {
    data class Success (val satuSiswa: DataSiswa) : StatusUIDetail
    object Error: StatusUIDetail
    object Loading: StatusUIDetail
}