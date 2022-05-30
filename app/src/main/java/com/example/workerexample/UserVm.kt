package com.example.workerexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workerexample.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserVm:ViewModel() {
    private val _data =  MutableStateFlow<List<User>?> (null)
    val data :StateFlow<List<User>?> = _data

    fun getData() = viewModelScope.launch {
        _data.emit(App.dao.getAll())
    }
}