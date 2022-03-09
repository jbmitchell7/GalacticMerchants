package cloud.jakemitchell.galacticmerchants.ui.dashboard

import android.content.Context
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.jakemitchell.galacticmerchants.MainActivity
import cloud.jakemitchell.galacticmerchants.login.LoginViewModel
import cloud.jakemitchell.galacticmerchants.network.SpaceTradersApi
import cloud.jakemitchell.galacticmerchants.network.data.User
import cloud.jakemitchell.galacticmerchants.network.data.UserData
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _accountInfo = MutableLiveData<User>()
    val accountInfo: LiveData<User> = _accountInfo

    fun viewAccount(auth: String) {
        viewModelScope.launch {
            try {
                val response = SpaceTradersApi.retrofitService.getAccount(auth)
                _accountInfo.value = response
            } catch (e: Exception) {
                println("account check failed")
                _accountInfo.value = User(UserData("", 0, 0, "", 0))
            }
        }
    }
}