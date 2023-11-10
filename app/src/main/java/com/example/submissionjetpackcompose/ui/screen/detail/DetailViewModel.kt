package com.example.submissionjetpackcompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionjetpackcompose.UiState
import com.example.submissionjetpackcompose.data.model.HeroesList
import com.example.submissionjetpackcompose.data.repository.HeroRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: HeroRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<HeroesList>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<HeroesList>>
        get() = _uiState

    fun getHeroById(heroesId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getHeroesById(heroesId))
        }
    }
}