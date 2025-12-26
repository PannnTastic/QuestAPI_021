package com.example.aplikasimysql.apiservice

import com.example.aplikasimysql.modeldata.DataSiswa
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ServiceApiSiswa {
    @GET("teman")
    suspend fun getSiswa():List<DataSiswa>

    @POST("teman")
    suspend fun insertSiswa(@Body dataSiswa: DataSiswa): retrofit2.Response<Void>

    @GET("teman/{id}")
    suspend fun getSatuSiswa(@Path("id") id: Int): DataSiswa

    @PUT("teman/{id}")
    suspend fun updateSiswa(@Path("id") id: Int, @Body dataSiswa: DataSiswa): Response<Void>

    @DELETE("teman/{id}")
    suspend fun deleteSiswa(@Path("id") id: Int): Response<Void>
}