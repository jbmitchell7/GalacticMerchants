package cloud.jakemitchell.galacticmerchants.ui.loans

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import cloud.jakemitchell.galacticmerchants.network.SpaceTradersApi
import cloud.jakemitchell.galacticmerchants.network.data.AvailableLoan
import cloud.jakemitchell.galacticmerchants.network.data.AvailableLoans
import kotlinx.coroutines.launch

class LoansViewModel(application: Application) : AndroidViewModel(application) {

    private val _loans = MutableLiveData<AvailableLoans>()
    val loans: LiveData<AvailableLoans> = _loans

    private val pref = application.getSharedPreferences("AUTHDATA", Context.MODE_PRIVATE)
    private val token = pref.getString("TOKEN", "")

    init {
        viewAvailableLoans(token)
    }

    private fun viewAvailableLoans(auth: String?) {
        viewModelScope.launch {
            try {
                val response = SpaceTradersApi.retrofitService.getAvailableLoans(auth)
                _loans.value = response
            } catch (e: Exception) {
                println("view available loans failed")
            }
        }
    }

}