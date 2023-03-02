package com.example.onlineshop.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.data.CategorySource
import com.example.onlineshop.data.models.*
import com.example.onlineshop.data.repository.ProductRepository
import com.example.onlineshop.other.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ProductRepository
): ViewModel() {

    val categoryLiveData: MutableLiveData<List<Category>> = MutableLiveData()
    val latestLiveData: MutableLiveData<Resource<LatestResponse>> = MutableLiveData()
    val flashSaleLiveData: MutableLiveData<Resource<FlashSaleResponse>> = MutableLiveData()

    init {
        getCategoryList()
        getLatest()
        getFlashSale()
    }

    private fun getLatest() =
        viewModelScope.launch {
            latestLiveData.postValue(Resource.Loading())
            val response = repository.getLatest()
            if (response.isSuccessful) {
                response.body().let { res ->
                    latestLiveData.postValue(Resource.Success(data = res))
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

    private fun getCategoryList() =
        viewModelScope.launch {
            categoryLiveData.postValue(CategorySource.getCategoryData())
        }
}