package cloud.jakemitchell.galacticmerchants

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import cloud.jakemitchell.galacticmerchants.network.SpaceTradersApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO change global to view model scope
        GlobalScope.launch {
            try {
                val result = SpaceTradersApi.retrofitService.getGameStatus()
                val loginBtn = findViewById<Button>(R.id.login)
                val tokenText = findViewById<EditText>(R.id.access_token)
                val userText = findViewById<EditText>(R.id.crew_name)

                val pref = getPreferences(Context.MODE_PRIVATE)
                val edit = pref.edit()
                loginBtn.setOnClickListener {
                    //will remove eventually
                    loginBtn.text = result.status
                    edit.putString("TOKEN", tokenText.text.toString())
                    edit.putString("USER", userText.text.toString())
                    edit.apply()
                }
            } catch (e: Exception) {
                println("Failed")
            }
        }
    }
}