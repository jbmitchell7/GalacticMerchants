package cloud.jakemitchell.galacticmerchants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cloud.jakemitchell.galacticmerchants.network.RetrofitHelper
import cloud.jakemitchell.galacticmerchants.network.SpaceTradersApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
    }
}