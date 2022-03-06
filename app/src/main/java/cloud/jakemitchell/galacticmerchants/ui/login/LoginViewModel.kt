package cloud.jakemitchell.galacticmerchants.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.jakemitchell.galacticmerchants.network.SpaceTradersApi
import cloud.jakemitchell.galacticmerchants.network.data.GameStatus
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel : ViewModel() {

    private val _gameStatus = MutableLiveData<GameStatus>()

    val gameStatus: LiveData<GameStatus> = _gameStatus

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
}