package cloud.jakemitchell.galacticmerchants.login

import androidx.lifecycle.*
import cloud.jakemitchell.galacticmerchants.network.SpaceTradersApi
import cloud.jakemitchell.galacticmerchants.network.data.*
import kotlinx.coroutines.launch
import kotlin.Exception

class LoginViewModel : ViewModel() {

    private val _authData = MutableLiveData<LoginData>()
    val authData: LiveData<LoginData> = _authData

    private val _account = MutableLiveData<User>()
    val account: LiveData<User> = _account

    private val _gameStatus = MutableLiveData<GameStatus>()
    val gameStatus: LiveData<GameStatus> = _gameStatus

    init {
        getStatus()
    }

    fun createAccount(username: String) {
        viewModelScope.launch {
            try {
                val response = SpaceTradersApi.retrofitService.createUsername(username)
                _authData.value = response
            } catch (e: Exception) {
                println("account creation failed")
                _authData.value = LoginData("",
                    LoginUserResponse("", 0, listOf(), listOf()))
            }
        }
    }

    fun viewAccount(auth: String) {
        viewModelScope.launch {
            try {
                val response = SpaceTradersApi.retrofitService.getAccount(auth)
                _account.value = response
            } catch (e: Exception) {
                println("account check failed")
                _account.value = User(UserData("", 0, 0, "", 0))
            }
        }
    }

    private fun getStatus() {
        viewModelScope.launch {
            try {
                val result = SpaceTradersApi.retrofitService.getGameStatus()
                _gameStatus.value = result
            } catch (e: Exception) {
                println("Failed to get game status")
            }
        }
    }
}