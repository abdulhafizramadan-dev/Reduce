package com.ahr.reduce.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.reduce.domain.repository.DatastoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) : ViewModel() {

    private val _onBoardingState = MutableStateFlow(false)
    val onBoardingState: StateFlow<Boolean> get() = _onBoardingState

    init {
        viewModelScope.launch {
            datastoreRepository.readOnBoardingState().collectLatest { onBoardingState ->
                _onBoardingState.value= onBoardingState
            }
        }
    }

}