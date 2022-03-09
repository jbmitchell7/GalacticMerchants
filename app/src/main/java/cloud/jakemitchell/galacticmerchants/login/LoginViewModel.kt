package cloud.jakemitchell.galacticmerchants.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.jakemitchell.galacticmerchants.network.SpaceTradersApi
import cloud.jakemitchell.galacticmerchants.network.data.GameStatus
import cloud.jakemitchell.galacticmerchants.network.data.LoginData
import kotlinx.coroutines.launch
import kotlin.Exception

class LoginViewModel : ViewModel() {

    private val _gameStatus = MutableLiveData<GameStatus>()
    val gameStatus: LiveData<GameStatus> = _gameStatus

    private val _authData = MutableLiveData<LoginData>()
    val authData: LiveData<LoginData> = _authData

    init {
        getStatus()
    }

    private fun getStatus() {
        viewModelScope.launch {
            try {
                val result = SpaceTradersApi.retrofitService.getGameStatus()
                _gameStatus.value = result
            } catch (e: Exception) {
                println("Failed")
            }
        }
    }

    fun createAccount(username: String) {
        viewModelScope.launch {
            try {
                val response = SpaceTradersApi.retrofitService.createUsername(username)
                _authData.value = response
                println(authData)
            } catch (e: Exception) {
                println("create user failed")
            }
        }
    }
}