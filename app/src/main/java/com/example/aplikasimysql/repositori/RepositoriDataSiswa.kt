package com.example.aplikasimysql.repositori

import com.example.aplikasimysql.apiservice.ServiceApiSiswa
import com.example.aplikasimysql.modeldata.DataSiswa
import retrofit2.Response

interface RepositoriDataSiswa {
    suspend fun getSiswa(): List<DataSiswa>
    suspend fun insertSiswa(dataSiswa: DataSiswa): retrofit2.Response<Void>

    suspend fun getSatuSiswa(id: Int): DataSiswa

    suspend fun updateSiswa(id: Int, dataSiswa: DataSiswa): retrofit2.Response<Void>
    suspend fun deleteSiswa(id: Int): retrofit2.Response<Void>

}

class JaringanRepositoriDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
): RepositoriDataSiswa{
    override suspend fun getSiswa(): List<DataSiswa> = serviceApiSiswa.getSiswa()
    override suspend fun insertSiswa(dataSiswa: DataSiswa)
    :retrofit2.Response<Void> = serviceApiSiswa.insertSiswa(dataSiswa)

    override suspend fun getSatuSiswa(id: Int): DataSiswa = serviceApiSiswa.getSatuSiswa(id)
    override suspend fun updateSiswa(id: Int,dataSiswa: DataSiswa): Response<Void>  = serviceApiSiswa.updateSiswa(id, dataSiswa)
    override suspend fun deleteSiswa(id: Int): Response<Void>  = serviceApiSiswa.deleteSiswa(id)
}