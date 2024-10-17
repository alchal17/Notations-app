package com.example.notations.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notations.database.daos.NotationDao
import com.example.notations.models.Notation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotationViewModel(private val notationDao: NotationDao) : ViewModel() {

    private val _notations = MutableStateFlow<List<Notation>>(emptyList())
    val notations = _notations.asStateFlow()

    init {
        viewModelScope.launch {
            notationDao.getAllNotations().collect { notationsList ->
                _notations.value = notationsList
            }
        }
    }

    fun addNotation(notation: Notation) {
        viewModelScope.launch {
            notationDao.addNotation(notation)
        }
    }

    fun deleteNotation(notation: Notation) {
        viewModelScope.launch { notationDao.deleteNotation(notation) }
    }

    fun findNotationById(id: Long, onResult: (Notation?) -> Unit) {
        viewModelScope.launch {
            val notation = notationDao.findNotationById(id)
            onResult(notation)
        }
    }

    fun updateNotation(notation: Notation) {
        viewModelScope.launch { notationDao.updateNotation(notation) }
    }
}