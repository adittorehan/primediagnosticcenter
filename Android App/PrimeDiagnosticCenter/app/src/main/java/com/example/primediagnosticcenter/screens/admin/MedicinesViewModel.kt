package com.example.primediagnosticcenter.screens.admin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.primediagnosticcenter.database.Medicine
import com.example.primediagnosticcenter.database.MyDatabaseDao
import kotlinx.coroutines.launch

class MedicinesViewModel(
    val database: MyDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var medcines = MutableLiveData<Medicine?>()




}

