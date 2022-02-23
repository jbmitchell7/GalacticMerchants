package cloud.jakemitchell.galacticmerchants

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

        val statusApi = RetrofitHelper.getInstance().create(SpaceTradersApiService::class.java)
        GlobalScope.launch {
            val result = statusApi.getGameStatus()
                if (result != null)
                    Log.d("Game Status: ", result.toString())
        }
    }
}