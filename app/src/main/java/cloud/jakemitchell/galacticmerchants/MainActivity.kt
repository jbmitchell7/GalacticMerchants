package cloud.jakemitchell.galacticmerchants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import cloud.jakemitchell.galacticmerchants.network.SpaceTradersApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            try {
                val result = SpaceTradersApi.retrofitService.getGameStatus()
                val loginBtn: Button = findViewById(R.id.login)
                loginBtn.setOnClickListener {
                    loginBtn.text = result.status
                }
            } catch (e: Exception) {
                println("Failed")
            }
        }
    }
}