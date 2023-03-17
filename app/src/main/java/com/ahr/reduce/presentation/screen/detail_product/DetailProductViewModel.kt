package com.ahr.reduce.presentation.screen.detail_product

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
class DetailProductViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val _productUiState = MutableStateFlow<UiState<Product>>(UiState.Idle)
    val detailProductUiState get() = _productUiState.asStateFlow()

    fun getDetailProduct(documentId: String) {
        viewModelScope.launch {
            firebaseRepository.getProductDetail(documentId).collect { apiState ->
                when (apiState) {
                    is ApiState.Loading -> _productUiState.value = UiState.Loading
                    is ApiState.Success -> _productUiState.value = UiState.Success(apiState.data)
                    is ApiState.Error -> _productUiState.value = UiState.Error(apiState.exception)
                }
            }
        }
    }

}