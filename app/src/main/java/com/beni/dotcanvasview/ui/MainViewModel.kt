package com.beni.dotcanvasview.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.beni.core.data.SampleRepository
import com.beni.core.data.local.preferences.CanvasSize
import com.beni.core.data.local.room.PointEntitiy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SampleRepository
) : ViewModel() {
    fun getCanvasSize() = repository.getCanvasSize().asLiveData()

    fun saveCanvasSize(canvasSize: CanvasSize) = viewModelScope.launch {
        repository.saveCanvasSize(canvasSize)
    }

    fun deleteCanvasSize() = viewModelScope.launch {
        repository.deleteCanvasSize()
    }

    fun getAllPont() = repository.getAllPont().asLiveData()

    fun getPointByPosition(x: Float, y: Float) = repository.getPointByPosition(x, y).asLiveData()

    fun insertPoints(list: List<PointEntitiy>) = viewModelScope.launch {
        repository.insertPoints(list)
    }

    fun removeAllPoint() = viewModelScope.launch {
        repository.removeAllPoint()
    }
}