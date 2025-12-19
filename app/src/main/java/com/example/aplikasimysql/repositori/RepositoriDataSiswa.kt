package com.example.aplikasimysql.repositori

import com.example.aplikasimysql.apiservice.ServiceApiSiswa
import com.example.aplikasimysql.modeldata.DataSiswa

interface RepositoriDataSiswa {
    suspend fun getSiswa(): List<DataSiswa>
    suspend fun insertSiswa(dataSiswa: DataSiswa): retrofit2.Response<Void>
}

class JaringanRepositoriDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
): RepositoriDataSiswa{
    override suspend fun getSiswa(): List<DataSiswa> = serviceApiSiswa.getSiswa()
    override suspend fun insertSiswa(dataSiswa: DataSiswa)
    :retrofit2.Response<Void> = serviceApiSiswa.insertSiswa(dataSiswa)
}