package cloud.jakemitchell.galacticmerchants.ui.systems

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import cloud.jakemitchell.galacticmerchants.network.SpaceTradersApi
import cloud.jakemitchell.galacticmerchants.network.data.Location
import cloud.jakemitchell.galacticmerchants.network.data.SystemLocations
import kotlinx.coroutines.launch

class SystemsViewModel(application: Application) : AndroidViewModel(application) {
    private val _systemLocations = MutableLiveData<SystemLocations>()
    val systemLocations: LiveData<SystemLocations> = _systemLocations

    private val pref = application.getSharedPreferences("AUTHDATA", Context.MODE_PRIVATE)
    val token = pref.getString("TOKEN", "")

    fun viewSystemLocations(auth: String?, currentSystem: String) {
        viewModelScope.launch {
            try {
                val response = SpaceTradersApi.retrofitService.getSystemLocations(auth, currentSystem)
                _systemLocations.value = response
            } catch (e: Exception) {
                println("view system locations failed")
            }
        }
    }
}