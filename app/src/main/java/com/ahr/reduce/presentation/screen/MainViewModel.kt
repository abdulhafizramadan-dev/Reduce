package com.ahr.reduce.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.domain.repository.DatastoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) : ViewModel() {

    private val _onBoardingState = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val onBoardingState: StateFlow<UiState<Boolean>> get() = _onBoardingState

    init {
        viewModelScope.launch {
            delay(1000)
            datastoreRepository.readOnBoardingState()
                .catch {
                    _onBoardingState.value = UiState.Error(it)
                }
                .collectLatest { onBoardingState ->
                    _onBoardingState.value = UiState.Success(onBoardingState)
                }
        }
    }

}