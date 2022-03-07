package cloud.jakemitchell.galacticmerchants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.jakemitchell.galacticmerchants.network.SpaceTradersApi
import cloud.jakemitchell.galacticmerchants.network.data.User
import kotlinx.coroutines.launch
import java.lang.Exception

class UserViewModel : ViewModel() {
    private val _user = MutableLiveData<User>()

    val user: LiveData<User> = _user

    init {
        getUserData()
    }

    private fun getUserData() {
        viewModelScope.launch {
            try {
                val response = SpaceTradersApi.retrofitService.getAccount()
                _user.value = response
            } catch (e: Exception) {
                println("Failed")
            }
        }
    }

}