package uz.abbosbek.codialtaskmusobaqa.presenter.page.mainpage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.abbosbek.codialtaskmusobaqa.data.model.request.LoginRequest
import uz.abbosbek.codialtaskmusobaqa.data.model.response.LoginResponse
import uz.abbosbek.codialtaskmusobaqa.domain.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    val logingLiveData: MutableLiveData<LoginResponse> = MutableLiveData()
    val loadingLiveData:MutableLiveData<Boolean> = MutableLiveData()
    val errormessage: MutableLiveData<String> = MutableLiveData()

    fun login(loginRequest: LoginRequest) {
        loadingLiveData.value = true
        repository.login(loginRequest).onEach {
            it.onSuccess {
                loadingLiveData.value = false
                logingLiveData.value = it
            }
            it.onFailure {
                errormessage.value = it.message
            }
        }.launchIn(viewModelScope)
    }


}