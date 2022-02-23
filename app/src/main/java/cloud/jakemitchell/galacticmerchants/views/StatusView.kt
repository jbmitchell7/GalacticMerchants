package cloud.jakemitchell.galacticmerchants.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.jakemitchell.galacticmerchants.network.SpaceTradersApi
import kotlinx.coroutines.launch

class StatusView : ViewModel() {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    init {
        getGameStatus()
    }

    private fun getGameStatus() {
        viewModelScope.launch {
            val listResult = SpaceTradersApi.retrofitService.getGameStatus()
            _status.value = listResult
        }
    }
}