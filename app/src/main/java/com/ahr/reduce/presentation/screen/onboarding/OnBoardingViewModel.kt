package com.ahr.reduce.presentation.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.reduce.domain.repository.DatastoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) : ViewModel() {

    fun saveOnBoardingState(state: Boolean) {
        viewModelScope.launch {
            datastoreRepository.saveOnBoardingState(state)
        }
    }

}