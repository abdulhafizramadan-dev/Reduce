package com.ahr.reduce.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.reduce.domain.data.ApiState
import com.ahr.reduce.domain.data.Product
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val _homeProductUiState = MutableStateFlow<UiState<List<Product>>>(UiState.Idle)
    val homeProductUiState get() = _homeProductUiState.asStateFlow()

    init {
        getHomeProduct()
    }

    fun getHomeProduct() {
        viewModelScope.launch {
            firebaseRepository.getHomeProduct().collect { apiState ->
                when (apiState) {
                    is ApiState.Loading -> _homeProductUiState.value = UiState.Loading
                    is ApiState.Success -> _homeProductUiState.value = UiState.Success(apiState.data)
                    is ApiState.Error -> _homeProductUiState.value = UiState.Error(apiState.exception)
                }
            }
        }
    }

}