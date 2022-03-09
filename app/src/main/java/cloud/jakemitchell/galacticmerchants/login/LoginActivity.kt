package cloud.jakemitchell.galacticmerchants.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cloud.jakemitchell.galacticmerchants.MainActivity
import cloud.jakemitchell.galacticmerchants.R
import cloud.jakemitchell.galacticmerchants.databinding.FragmentSystemsBinding
import cloud.jakemitchell.galacticmerchants.network.SpaceTradersApi
import cloud.jakemitchell.galacticmerchants.network.data.GameStatus
import cloud.jakemitchell.galacticmerchants.network.data.LoginData
import cloud.jakemitchell.galacticmerchants.ui.systems.SystemsViewModel

class LoginActivity : AppCompatActivity() {
    private val model: LoginViewModel by viewModels()

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val statusObserver = Observer<GameStatus> { gameStatus ->
            //sets game status message on login screen
            val statusText = findViewById<TextView>(R.id.status_text)
            if (gameStatus.status == "spacetraders is currently online and available to play") {
                statusText.text = "Game is Online"
                statusText.setTextColor(Color.parseColor("#08A045"))
            } else {
                statusText.text ="Game is Offline. Please Try Again Later"
                statusText.setTextColor(Color.parseColor("#bd251a"))
            }
        }
        val loginObserver = Observer<LoginData> { _ ->
            //sets user and token for sharedpreferences
            val tokenText = findViewById<EditText>(R.id.access_token)
            val userText = findViewById<EditText>(R.id.crew_name)
            //initializes sharedpreferences
            val pref = getPreferences(Context.MODE_PRIVATE)
            val edit = pref.edit()
            val loginBtn = findViewById<Button>(R.id.login)
            loginBtn.setOnClickListener {
                model.createAccount(userText.text.toString())
                //sets input to shared preferences
                edit.putString("TOKEN", tokenText.text.toString())
                edit.putString("USER", userText.text.toString())
                edit.apply()
                //navigate to home screen
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        model.gameStatus.observe(this, statusObserver)
        model.authData.observe(this, loginObserver)
    }

}