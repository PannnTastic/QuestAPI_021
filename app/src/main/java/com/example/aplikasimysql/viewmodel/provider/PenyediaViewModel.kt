package com.example.aplikasimysql.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.aplikasimysql.repositori.AplikasiDataSiswa
import com.example.aplikasimysql.viewmodel.DetailViewModel
import com.example.aplikasimysql.viewmodel.EditViewModel
import com.example.aplikasimysql.viewmodel.EntryViewModel
import com.example.aplikasimysql.viewmodel.HomeViewModel

fun CreationExtras.aplikasiDataSiswa(): AplikasiDataSiswa = (
        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as
        AplikasiDataSiswa
        )
object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiDataSiswa().container.repositoriDataSiswa)
        }
        initializer {
            EntryViewModel(aplikasiDataSiswa().container.repositoriDataSiswa)
        }
        initializer {
            DetailViewModel(this.createSavedStateHandle(),aplikasiDataSiswa().container.repositoriDataSiswa)
        }
        initializer {
            EditViewModel(this.createSavedStateHandle(),aplikasiDataSiswa().container.repositoriDataSiswa)
        }
    }
}