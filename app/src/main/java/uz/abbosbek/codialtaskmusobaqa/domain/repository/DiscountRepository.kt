package uz.abbosbek.codialtaskmusobaqa.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.abbosbek.codialtaskmusobaqa.data.model.response.UserResponse
import uz.abbosbek.codialtaskmusobaqa.data.model.response.Warehouse

interface DiscountRepository {
    fun getAdminDiscount(token:String): Flow<Result<UserResponse>>
}