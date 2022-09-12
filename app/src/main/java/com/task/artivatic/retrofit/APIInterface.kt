package com.task.artivatic.retrofit

import com.task.artivatic.pojo.ExampleData
import retrofit2.Response
import retrofit2.http.GET

interface APIInterface {

    @GET("c4ab4c1c-9a55-4174-9ed2-cbbe0738eedf")
    suspend fun getApiResponse(): Response<ExampleData>
}