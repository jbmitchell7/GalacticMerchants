package cloud.jakemitchell.galacticmerchants.login

import android.os.Bundle
import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import cloud.jakemitchell.galacticmerchants.NavigationActivity
import cloud.jakemitchell.galacticmerchants.R
import cloud.jakemitchell.galacticmerchants.network.data.GameStatus

class LoginActivity : AppCompatActivity() {
    private val model: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val statusObserver = Observer<GameStatus> { gameStatus ->
            val loginBtn = findViewById<Button>(R.id.login)
            val tokenText = findViewById<EditText>(R.id.access_token)
            val userText = findViewById<EditText>(R.id.crew_name)
            val pref = getPreferences(Context.MODE_PRIVATE)
            val edit = pref.edit()
            loginBtn.setOnClickListener {
                //will remove eventually
                loginBtn.text = gameStatus.status
                val intent = Intent(this, NavigationActivity::class.java)
                startActivity(intent)
                edit.apply()
            }
        }
        model.gameStatus.observe(this, statusObserver)
    }

}