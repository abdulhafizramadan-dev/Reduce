package com.ahr.reduce.presentation.screen.checkout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.reduce.domain.data.ApiState
import com.ahr.reduce.domain.data.DetailAddressForm
import com.ahr.reduce.domain.data.Product
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val _productUiState = MutableStateFlow<UiState<List<Product>>>(UiState.Idle)
    val detailProductUiState get() = _productUiState.asStateFlow()

    var userAddressState by mutableStateOf("")
        private set

    var checkoutButtonLoadingState by mutableStateOf(false)
        private set

    var saveDetailAddressState: UiState<Boolean> by mutableStateOf(UiState.Idle)
        private set

    init {
        getDetailAddress()
    }

    private fun getDetailAddress() {
        checkoutButtonLoadingState = true
        viewModelScope.launch {
            firebaseRepository.getUserAddress().collect { apiState ->
                when (apiState) {
                    is ApiState.Loading -> saveDetailAddressState = UiState.Loading
                    is ApiState.Success -> {
                        checkoutButtonLoadingState = false
                        userAddressState = apiState.data.completeAddress
                    }
                    is ApiState.Error -> { checkoutButtonLoadingState = false }
                }
            }
        }
    }

    fun getDetailProduct(documentId: String) {
        viewModelScope.launch {
            firebaseRepository.getProductDetail(documentId).collect { apiState ->
                when (apiState) {
                    is ApiState.Loading -> _productUiState.value = UiState.Loading
                    is ApiState.Success -> _productUiState.value = UiState.Success(listOf(apiState.data))
                    is ApiState.Error -> _productUiState.value = UiState.Error(apiState.exception)
                }
            }
        }
    }


    fun saveDetailAddress() {
        saveDetailAddressState = UiState.Loading
//        viewModelScope.launch {
//            saveDetailAddressState = try {
//                val saveResult = firebaseRepository.saveUserAddress(detailAddressForm.value)
//                UiState.Success(saveResult)
//            } catch (exception: Exception) {
//                UiState.Error(exception)
//            }
//        }
    }
}