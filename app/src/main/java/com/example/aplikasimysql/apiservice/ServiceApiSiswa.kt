package com.example.aplikasimysql.apiservice

import com.example.aplikasimysql.modeldata.DataSiswa
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceApiSiswa {
    @GET("teman")
    suspend fun getSiswa():List<DataSiswa>

    @POST("teman")
    suspend fun insertSiswa(@Body dataSiswa: DataSiswa): retrofit2.Response<Void>
}