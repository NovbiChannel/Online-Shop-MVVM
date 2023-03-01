package com.example.onlineshop.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.data.models.FlashSale
import com.example.onlineshop.data.models.FlashSaleResponse
import com.example.onlineshop.data.models.Latest
import com.example.onlineshop.data.models.LatestResponse
import com.example.onlineshop.data.repository.ProductRepository
import com.example.onlineshop.other.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ProductRepository
): ViewModel() {

    val latestLiveData: MutableLiveData<Resource<LatestResponse>> = MutableLiveData()
    val flashSaleLiveData: MutableLiveData<Resource<FlashSaleResponse>> = MutableLiveData()

    init {
        getLatest()
        getFlashSale()
    }

    private fun getLatest() =
        viewModelScope.launch {
            latestLiveData.postValue(Resource.Loading())
            val response = repository.getLatest()
            if (response.isSuccessful) {
                response.body().let { res ->
                    latestLiveData.postValue(Resource.Success(res))
                }
            } else {
                latestLiveData.postValue(Resource.Error(message = response.message()))
            }
        }

    private fun getFlashSale() =
        viewModelScope.launch {
            flashSaleLiveData.postValue(Resource.Loading())
            val response = repository.getFlashSale()
            if (response.isSuccessful) {
                response.body().let { res ->
                    flashSaleLiveData.postValue(Resource.Success(data = res))
                }
            } else {
                flashSaleLiveData.postValue(Resource.Error(message = response.message()))
            }
        }
}