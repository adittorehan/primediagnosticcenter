package com.example.primediagnosticcenter.screens.admin

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.primediagnosticcenter.database.MyDatabaseDao

class MedicinesViewModelFactory(
    private val dataSource: MyDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicinesViewModel::class.java)) {
            return MedicinesViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}