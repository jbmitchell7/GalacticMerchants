package cloud.jakemitchell.galacticmerchants.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import androidx.activity.viewModels
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import cloud.jakemitchell.galacticmerchants.MainActivity
import cloud.jakemitchell.galacticmerchants.R
import cloud.jakemitchell.galacticmerchants.network.data.GameStatus
import cloud.jakemitchell.galacticmerchants.network.data.LoginData
import cloud.jakemitchell.galacticmerchants.network.data.User
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private val modelLogin: LoginViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val intent = Intent(this, MainActivity::class.java)
        //initializes shared preferences
        val pref = getSharedPreferences("AUTHDATA", Context.MODE_PRIVATE)
        val edit = pref.edit()
        //navigates to dashboard if login is saved
        val currToken = pref.getString("TOKEN", "")
        if (currToken != null) {
            if(currToken.isNotEmpty()){
                startActivity(intent)
                finish()
            }
        }
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

        val userText = findViewById<EditText>(R.id.crew_name)
        val tokenText = findViewById<EditText>(R.id.access_token)

        fun makeSnackBar(message: String) {
            val contextView = findViewById<View>(R.id.failed_login_snackbar)
            val snackBar = Snackbar.make(contextView, message, Snackbar.LENGTH_SHORT)
            val snackBarView = snackBar.view
            snackBarView.setBackgroundColor(Color.RED)
            val textView =
                snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            textView.setTextColor(Color.WHITE)
            snackBar.show()
        }

        val loginObserver = Observer<LoginData> { authData ->
            if(authData.token.isNotEmpty()) {
                //sets input to shared preferences
                edit.putString("TOKEN", authData.token)
                edit.putString("USER", userText.text.toString())
                edit.apply()
                //navigate to home screen
                startActivity(intent)
                finish()
            } else {
                makeSnackBar("Username has already been claimed.")
            }
        }

        val accountObserver = Observer<User> { account ->
            if(account.user.username.isNotEmpty()) {
                edit.putString("TOKEN", tokenText.text.toString())
                edit.putString("USER", account.user.username)
                edit.apply()
                startActivity(intent)
                finish()
            } else {
                makeSnackBar("Token is not correct.")
            }
        }

        val loginBtn = findViewById<Button>(R.id.login_btn)
        loginBtn.setOnClickListener {
            //checks if user attempts to enter token
            if (tokenText.text.isNotEmpty()) {
                val tokenValue = tokenText.text
                modelLogin.viewAccount("Bearer $tokenValue")
            } else {
                modelLogin.createAccount(userText.text.toString())
            }
        }

        modelLogin.authData.observe(this, loginObserver)
        modelLogin.gameStatus.observe(this, statusObserver)
        modelLogin.account.observe(this, accountObserver)
    }
}