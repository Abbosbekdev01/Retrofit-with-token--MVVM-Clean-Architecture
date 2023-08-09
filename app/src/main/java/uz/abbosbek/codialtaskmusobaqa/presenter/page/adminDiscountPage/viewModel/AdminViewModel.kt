package uz.abbosbek.codialtaskmusobaqa.presenter.page.adminDiscountPage.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.abbosbek.codialtaskmusobaqa.data.model.response.LoginResponse
import uz.abbosbek.codialtaskmusobaqa.data.model.response.UserResponse
import uz.abbosbek.codialtaskmusobaqa.domain.repository.DiscountRepository
import javax.inject.Inject


@HiltViewModel
class AdminViewModel @Inject constructor(private val repository: DiscountRepository) : ViewModel() {

    val discountsLiveData: MutableLiveData<UserResponse> = MutableLiveData()
    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val errormessage: MutableLiveData<String> = MutableLiveData()


    fun getDiscount(token:String){
        loadingLiveData.value = true
        repository.getAdminDiscount(token).onEach {
            it.onSuccess {
                loadingLiveData.value = false
                discountsLiveData.value = it
            }
            it.onFailure {
                errormessage.value = it.message
                loadingLiveData.value = false
            }
        }.launchIn(viewModelScope)
    }

}