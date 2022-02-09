package com.example.primediagnosticcenter.screens.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.primediagnosticcenter.database.MyDatabaseDao
import com.example.primediagnosticcenter.database.User
import kotlinx.coroutines.launch

class RegisterViewModel(
    val database: MyDatabaseDao,
    application: Application
) : AndroidViewModel(application) {
    fun onRegister() {
        viewModelScope.launch {
            val user = User()
            insert(user)
        }
    }

    private suspend fun insert(user: User) {
        database.insert(user)
    }
}