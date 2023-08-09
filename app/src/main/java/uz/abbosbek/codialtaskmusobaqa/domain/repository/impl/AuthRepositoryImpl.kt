package uz.abbosbek.codialtaskmusobaqa.domain.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.abbosbek.codialtaskmusobaqa.data.model.request.LoginRequest
import uz.abbosbek.codialtaskmusobaqa.data.model.response.LoginResponse
import uz.abbosbek.codialtaskmusobaqa.data.source.ApiService
import uz.abbosbek.codialtaskmusobaqa.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val api: ApiService, private val gson: Gson) :
    AuthRepository {


    override fun login(lohinRequest: LoginRequest): Flow<Result<LoginResponse>> =
        flow<Result<LoginResponse>> {
            val response = api.login(lohinRequest)
            if (response.isSuccessful) {
                emit(Result.success(response.body()!!))
            } else {
                val messagerrro =
                    gson.fromJson(response.errorBody()!!.string().toString(), String::class.java)
                emit(Result.failure(Exception(messagerrro.toString())))
            }
        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)


}