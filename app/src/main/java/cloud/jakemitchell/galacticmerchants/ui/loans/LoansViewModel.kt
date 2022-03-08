package cloud.jakemitchell.galacticmerchants.ui.loans

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoansViewModel : ViewModel () {
    private val _text = MutableLiveData<String>().apply {
        value = "This is loans Fragment"
    }
    val text: LiveData<String> = _text
}