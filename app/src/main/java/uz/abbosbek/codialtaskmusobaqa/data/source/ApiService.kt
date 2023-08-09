package uz.abbosbek.codialtaskmusobaqa.data.source

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import uz.abbosbek.codialtaskmusobaqa.data.model.request.LoginRequest
import uz.abbosbek.codialtaskmusobaqa.data.model.response.LoginResponse
import uz.abbosbek.codialtaskmusobaqa.data.model.response.UserResponse
import uz.abbosbek.codialtaskmusobaqa.data.model.response.Warehouse

interface ApiService {


    @POST("api/token/")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    suspend fun getDiscount(@Header("Authorization ") token:String): Response<UserResponse>

}