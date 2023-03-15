package com.ahr.reduce.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.reduce.data.data.ProductRealm
import com.ahr.reduce.domain.repository.RealmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val realmRepository: RealmRepository
) : ViewModel() {

    private val _homeProducts = MutableStateFlow(emptyList<ProductRealm>())
    val homeProducts get() = _homeProducts.asStateFlow()

    fun getHomeProduct() {
        viewModelScope.launch {
            realmRepository.getHomeProduct().collect { homeProducts ->
                _homeProducts.value = homeProducts
            }
        }
    }

}