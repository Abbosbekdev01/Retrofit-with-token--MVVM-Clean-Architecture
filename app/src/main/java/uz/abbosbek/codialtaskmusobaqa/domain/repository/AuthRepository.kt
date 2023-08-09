package uz.abbosbek.codialtaskmusobaqa.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.abbosbek.codialtaskmusobaqa.data.model.request.LoginRequest
import uz.abbosbek.codialtaskmusobaqa.data.model.response.LoginResponse

interface AuthRepository {
    fun login(loginRequest: LoginRequest): Flow<Result<LoginResponse>>

}