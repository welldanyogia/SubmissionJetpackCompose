package com.example.submissionjetpackcompose.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionjetpackcompose.UiState
import com.example.submissionjetpackcompose.data.model.HeroesList
import com.example.submissionjetpackcompose.data.repository.HeroRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HeroRepository
):ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<HeroesList>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<HeroesList>>>
        get() = _uiState
    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllHeroes() {
        viewModelScope.launch {
            repository.getAllHeroes()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { heroesList ->
                    _uiState.value = UiState.Success(heroesList)
                }

        }
    }
    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            val filteredHeroes = repository.searchHeroes(_query.value)
            _uiState.value = UiState.Success(filteredHeroes)
        }
    }
}