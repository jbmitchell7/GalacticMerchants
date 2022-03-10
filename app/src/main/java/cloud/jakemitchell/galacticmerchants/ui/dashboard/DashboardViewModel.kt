package cloud.jakemitchell.galacticmerchants.ui.dashboard

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import cloud.jakemitchell.galacticmerchants.network.SpaceTradersApi
import cloud.jakemitchell.galacticmerchants.network.data.User
import cloud.jakemitchell.galacticmerchants.network.data.UserData
import kotlinx.coroutines.launch

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val _accountInfo = MutableLiveData<User>()
    val accountInfo: LiveData<User> = _accountInfo

    private val pref = application.getSharedPreferences("AUTHDATA", Context.MODE_PRIVATE)
    private val token = pref.getString("TOKEN", "")

    init {
        viewAccount(token)
    }

    fun viewAccount(auth: String?) {
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