package com.task.artivatic.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.artivatic.pojo.ExampleData
import com.task.artivatic.repository.ExampleRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: ExampleRepository,
) : ViewModel() {

    val apiResponseLiveData: LiveData<ExampleData?>
        get() = repository.apiResponses

    fun callApi() {
        viewModelScope.launch {
            repository.getApiResponse()
        }
    }

}

