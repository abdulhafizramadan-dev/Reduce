package com.ahr.reduce.presentation.screen.profile_setting

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.reduce.domain.data.ApiState
import com.ahr.reduce.domain.data.ProfileSettingForm
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.domain.repository.FirebaseRepository
import com.ahr.reduce.util.isEmailFormat
import com.ahr.reduce.util.toLocalDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ProfileSettingViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val _profileSettingForm = MutableStateFlow(ProfileSettingForm())
    val profileSettingForm get() = _profileSettingForm.asStateFlow()

    var isFirstNameNotValid by mutableStateOf(false)
        private set
    var isLastNameNotValid by mutableStateOf(false)
        private set
    var isEmailNotValid by mutableStateOf(false)
        private set
    var isTelephoneNotValid by mutableStateOf(false)
        private set

    var saveButtonLoadingState by mutableStateOf(false)
        private set

    val birthDateInLocalDate @RequiresApi(Build.VERSION_CODES.O)
    get() = _profileSettingForm.map {
        val dateBirth = it.birthDate.ifEmpty {
            val currentDate = LocalDate.now().toString()
            updateBirthDate(currentDate)
            currentDate
        }
        dateBirth.toLocalDate()
    }

    var saveUserProfileState: UiState<Boolean> by mutableStateOf(UiState.Idle)
        private set

    val allFormValid get() = _profileSettingForm.map {  profileSettingForm ->
        profileSettingForm.firstName.isNotEmpty() &&
        profileSettingForm.lastName.isNotEmpty() &&
        profileSettingForm.email.isNotEmpty() &&
        profileSettingForm.email.isEmailFormat() &&
        profileSettingForm.birthDate.isNotEmpty() &&
        profileSettingForm.gender.isNotEmpty()
    }

    init {
        getUserProfile()
    }

    private fun getUserProfile() {
        saveButtonLoadingState = true
        viewModelScope.launch {
            firebaseRepository.getUser().collect { apiState ->
                when (apiState) {
                    is ApiState.Loading -> saveUserProfileState = UiState.Loading
                    is ApiState.Success -> {
                        saveButtonLoadingState = false
                        _profileSettingForm.value = apiState.data
                    }
                    is ApiState.Error -> { saveButtonLoadingState = false }
                }
            }
        }
    }

    fun saveUserProfile() {
        saveUserProfileState = UiState.Loading
        viewModelScope.launch {
            saveUserProfileState = try {
                val saveResult = firebaseRepository.saveUser(profileSettingForm.value)
                UiState.Success(saveResult)
            } catch (exception: Exception) {
                UiState.Error(exception)
            }
        }
    }

    fun updateFirstName(firstName: String) {
        _profileSettingForm.update { it.copy(firstName = firstName) }
        isFirstNameNotValid = firstName.isEmpty()
    }

    fun updateLastName(lastName: String) {
        _profileSettingForm.update { it.copy(lastName = lastName) }
        isLastNameNotValid = lastName.isEmpty()
    }

    fun updateEmail(email: String) {
        _profileSettingForm.update { it.copy(email = email) }
        isEmailNotValid = email.isEmpty() || !email.isEmailFormat()
    }

    fun updateTelephone(telephone: String) {
        _profileSettingForm.update { it.copy(telephone = telephone) }
        isTelephoneNotValid = telephone.isEmpty()
    }

    fun updateBirthDate(birthDate: String) {
        _profileSettingForm.update { it.copy(birthDate = birthDate) }
    }

    fun updateGender(gender: String) {
        _profileSettingForm.update { it.copy(gender = gender) }
    }

    fun updateSaveButtonLoadingState(state: Boolean) {
        saveButtonLoadingState = state
    }
}