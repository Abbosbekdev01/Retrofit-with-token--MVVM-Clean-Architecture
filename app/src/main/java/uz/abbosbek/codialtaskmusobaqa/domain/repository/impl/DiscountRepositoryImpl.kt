package uz.abbosbek.codialtaskmusobaqa.domain.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.abbosbek.codialtaskmusobaqa.data.model.response.UserResponse
import uz.abbosbek.codialtaskmusobaqa.data.model.response.Warehouse
import uz.abbosbek.codialtaskmusobaqa.data.source.ApiService
import uz.abbosbek.codialtaskmusobaqa.domain.repository.DiscountRepository
import javax.inject.Inject

class DiscountRepositoryImpl @Inject constructor(private val api:ApiService,private val gson: Gson): DiscountRepository {

    override fun getAdminDiscount(token:String): Flow<Result<UserResponse>> = flow<Result<UserResponse>> {
        val response = api.getDiscount(token)
        if (response.isSuccessful){
            emit(Result.success(response.body()!!))
        }else{
            val messagerrro =
                gson.fromJson(response.errorBody()!!.string().toString(), String::class.java)
            emit(Result.failure(Exception(messagerrro.toString())))
        }

    }.flowOn(Dispatchers.IO)


}