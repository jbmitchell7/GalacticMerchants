package cloud.jakemitchell.galacticmerchants.ui.systems

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SystemsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is systems Fragment"
    }
    val text: LiveData<String> = _text
}