package com.ahr.reduce.presentation.screen.market

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
class MarketViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val _marketUiState = MutableStateFlow<UiState<List<Product>>>(UiState.Idle)
    val marketUiState get() = _marketUiState.asStateFlow()

    init {
        getHomeProduct()
    }

    fun getHomeProduct() {
        viewModelScope.launch {
            firebaseRepository.getMarketProduct().collect { apiState ->
                when (apiState) {
                    is ApiState.Loading -> _marketUiState.value = UiState.Loading
                    is ApiState.Success -> _marketUiState.value = UiState.Success(apiState.data)
                    is ApiState.Error -> _marketUiState.value = UiState.Error(apiState.exception)
                }
            }
        }
    }

}