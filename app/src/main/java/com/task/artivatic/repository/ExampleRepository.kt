package com.task.artivatic.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.artivatic.retrofit.APIInterface
import com.task.artivatic.utils.NetworkResult
import com.task.artivatic.utils.SafeApiCall
import com.task.artivatic.pojo.ExampleData
import retrofit2.Response
import javax.inject.Inject

class ExampleRepository @Inject constructor(private val APIInterface: APIInterface) {

    private val _apiResponses = MutableLiveData<ExampleData?>()
    val apiResponses: LiveData<ExampleData?>
        get() = _apiResponses

    suspend fun getApiResponse() {
        val result: NetworkResult<Response<ExampleData>> = SafeApiCall.safeApiCall {
            APIInterface.getApiResponse()
        }
        when (result) {
            is NetworkResult.Success -> {
                if (result.data.isSuccessful && result.data.body() != null) {
                    _apiResponses.postValue(result.data.body())
                }
            }
            is NetworkResult.Error -> { //Handle error
                Log.e("Error", result.message.toString())
                _apiResponses.postValue(null)
            }
        }

    }

}