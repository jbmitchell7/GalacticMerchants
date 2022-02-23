package cloud.jakemitchell.galacticmerchants

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

    fun onLogin(view: View) {
        val pref = getPreferences(Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("USER", findViewById(R.id.crew_name))
        editor.putString("TOKEN", findViewById(R.id.access_token))
        editor.apply()
    }
}