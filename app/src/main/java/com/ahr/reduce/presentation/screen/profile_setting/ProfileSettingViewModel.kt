package com.ahr.reduce.presentation.screen.profile_setting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ahr.reduce.domain.data.ProfileSettingForm
import com.ahr.reduce.util.isEmailFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileSettingViewModel @Inject constructor() : ViewModel() {

    private val _profileSettingForm = MutableStateFlow(ProfileSettingForm(
        firstName = "Abdul",
        lastName = "Hafiz Ramadan",
        email = "abdulhafizramadan@gmail.com",
        telephone = "0822880117760",
        birthDate = "22-11-2002",
        gender = "Laki-Laki"
    ))
    val profileSettingForm get() = _profileSettingForm.asStateFlow()

    var isFirstNameNotValid by mutableStateOf(false)
        private set
    var isLastNameNotValid by mutableStateOf(false)
        private set
    var isEmailNotValid by mutableStateOf(false)
        private set
    var isTelephoneNotValid by mutableStateOf(false)
        private set
    var isBirthDateNotValid by mutableStateOf(false)
        private set

    val allFormValid get() = _profileSettingForm.map {  profileSettingForm ->
        profileSettingForm.firstName.isNotEmpty() &&
        profileSettingForm.lastName.isNotEmpty() &&
        profileSettingForm.email.isNotEmpty() &&
        profileSettingForm.email.isEmailFormat() &&
        profileSettingForm.birthDate.isNotEmpty() &&
        profileSettingForm.gender.isNotEmpty()
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
        isBirthDateNotValid = birthDate.isEmpty()
    }

    fun updateGender(gender: String) {
        _profileSettingForm.update { it.copy(gender = gender) }
    }
}