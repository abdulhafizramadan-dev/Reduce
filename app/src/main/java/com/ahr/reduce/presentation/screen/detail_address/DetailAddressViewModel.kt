package com.ahr.reduce.presentation.screen.detail_address

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.reduce.domain.data.ApiState
import com.ahr.reduce.domain.data.DetailAddressForm
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailAddressViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val _detailAddressForm = MutableStateFlow(DetailAddressForm())
    val detailAddressForm get() = _detailAddressForm.asStateFlow()

    var isStreetNameNotValid by mutableStateOf(false)
        private set
    var isWardNotValid by mutableStateOf(false)
        private set
    var isSubdistrictNotValid by mutableStateOf(false)
        private set
    var isRegencyNotValid by mutableStateOf(false)
        private set
    var isProvinceNotValid by mutableStateOf(false)
        private set
    var isCompleteAddressNotValid by mutableStateOf(false)
        private set

    var saveButtonLoadingState by mutableStateOf(false)
        private set

    val allFormValid get() = _detailAddressForm.map { profileSettingForm ->
        profileSettingForm.streetName.isNotEmpty() &&
        profileSettingForm.ward.isNotEmpty() &&
        profileSettingForm.subdistrict.isNotEmpty() &&
        profileSettingForm.regency.isNotEmpty() &&
        profileSettingForm.province.isNotEmpty() &&
        profileSettingForm.completeAddress.isNotEmpty()
    }

    var saveDetailAddressState: UiState<Boolean> by mutableStateOf(UiState.Idle)
        private set

    init {
        getDetailAddress()
    }

    private fun getDetailAddress() {
        saveButtonLoadingState = true
        viewModelScope.launch {
            firebaseRepository.getUserAddress().collect { apiState ->
                when (apiState) {
                    is ApiState.Loading -> saveDetailAddressState = UiState.Loading
                    is ApiState.Success -> {
                        saveButtonLoadingState = false
                        _detailAddressForm.value = apiState.data
                    }
                    is ApiState.Error -> { saveButtonLoadingState = false }
                }
            }
        }
    }

    fun saveDetailAddress() {
        saveDetailAddressState = UiState.Loading
        viewModelScope.launch {
            saveDetailAddressState = try {
                val saveResult = firebaseRepository.saveUserAddress(detailAddressForm.value)
                UiState.Success(saveResult)
            } catch (exception: Exception) {
                UiState.Error(exception)
            }
        }
    }

    fun updateStreetName(streetName: String) {
        _detailAddressForm.update { it.copy(streetName = streetName) }
        isStreetNameNotValid = streetName.isEmpty()
    }

    fun updateWard(ward: String) {
        _detailAddressForm.update { it.copy(ward = ward) }
        isWardNotValid = ward.isEmpty()
    }

    fun updateSubdistrict(subdistrict: String) {
        _detailAddressForm.update { it.copy(subdistrict = subdistrict) }
        isSubdistrictNotValid = subdistrict.isEmpty()
    }

    fun updateRegency(regency: String) {
        _detailAddressForm.update { it.copy(regency = regency) }
        isRegencyNotValid = regency.isEmpty()
    }

    fun updateProvince(province: String) {
        _detailAddressForm.update { it.copy(province = province) }
        isProvinceNotValid = province.isEmpty()
    }

    fun updateCompleteAddress(completeAddress: String) {
        _detailAddressForm.update { it.copy(completeAddress = completeAddress) }
        isCompleteAddressNotValid = completeAddress.isEmpty()
    }

    fun updateSaveButtonLoadingState(state: Boolean) {
        saveButtonLoadingState = state
    }
}